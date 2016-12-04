package com.shop.base.action;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shop.base.entity.JokeAgreeHisModel;
import com.shop.base.entity.JokeCommetModel;
import com.shop.base.entity.JokeImgModel;
import com.shop.base.entity.JokeTextModel;
import com.shop.base.entity.JokeUserModel;
import com.shop.base.model.Result;
import com.shop.base.service.JokeAgreeHisService;
import com.shop.base.service.JokeCommetService;
import com.shop.base.service.JokeImgService;
import com.shop.base.service.JokeTextService;
import com.shop.base.service.JokeUserService;
import com.shop.base.util.StringUtil;

@Controller
@RequestMapping("/joke")
public class JokeAction {
	private static final Logger logger = LoggerFactory.getLogger(JokeAction.class);

	@Reference
	private JokeImgService jokeImgService;
	@Reference
	private JokeTextService jokeTextService;
	@Reference
	private JokeUserService jokeUserService;
	@Reference
	private JokeCommetService jokeCommetService;
	@Reference
	private JokeAgreeHisService jokeAgreeHisService;

	/**
	 * 获取图片列表json
	 * 
	 * @param jokeImg
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getJokeImgList")
	@ResponseBody
	public Result getJokeImgList(JokeImgModel jokeImg) {
		Result res = new Result();
		logger.info("查询图片列表start");
		List<JokeImgModel> jokeImgList = jokeImgService.queryJokeImgNewList(jokeImg);
		logger.info("查询图片列表end");
		res.setCode(Result.CODE_OK);
		res.setData(jokeImgList);
		return res;
	}

	/**
	 * 获取文本笑话列表json
	 * 
	 * @param jokeText
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getJokeTextList")
	@ResponseBody
	public Result getJokeTextList(JokeTextModel jokeText) {
		Result res = new Result();
		logger.info("查询文字列表start");
		List<JokeTextModel> jokeTextList = jokeTextService.queryJokeTextNewList(jokeText);
		logger.info("查询文字列表end");
		res.setCode(Result.CODE_OK);
		res.setData(jokeTextList);
		return res;
	}
	
	/**
	 * 保存用户信息
	 * @param jokeUser
	 * @return
	 */
	@RequestMapping("/saveJokeUser")
	@ResponseBody
	public Result saveJokeUser(JokeUserModel jokeUser) {
		Result res = new Result();
		jokeUser.setRegistTime(new Date());
		try{
			int count = jokeUserService.insert(jokeUser);
			if(count==1){
				res.setCode(Result.CODE_OK);
			}else{
				res.setCode(Result.CODE_ERROR);
				res.setMsg("保存用户信息失败");
				return res;
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			res.setCode(Result.CODE_ERROR);
			res.setMsg("保存用户信息失败");
		}
		return res;
	}
	
	/**
	 * 保存评论信息
	 * @param jokeUser
	 * @return
	 */
	@RequestMapping("/saveJokeCommet")
	@ResponseBody
	public Result saveJokeCommet(JokeCommetModel jokeCommet) {
		Result res = new Result();
		if(StringUtil.isEmpty(jokeCommet.getCommetJokeId())){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("段子ID不能为空");
			return res;
		}
		if(StringUtil.isEmpty(jokeCommet.getCommetJokeType())){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("段子类型不能为空");
			return res;
		}
		if(StringUtil.isEmpty(jokeCommet.getUserCode())){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("用户账号不能为空");
			return res;
		}
		jokeCommet.setCommetTime(new Date());
		jokeCommet.setCommetAgreeCount(0);
		jokeCommet.setCommetDisagreeCount(0);
		try{
			int count = jokeCommetService.insert(jokeCommet);
			if(count==1){
				res.setCode(Result.CODE_OK);
			}else{
				res.setCode(Result.CODE_ERROR);
				res.setMsg("保存评论失败");
				return res;
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			res.setCode(Result.CODE_ERROR);
			res.setMsg("评论保存失败");
		}
		return res;
	}
	
	/**
	 * 点赞
	 * @param jokeUser
	 * @return
	 */
	@RequestMapping("/commetAgree")
	@ResponseBody
	public Result commetAgree(String commetId,String userCode,String agreeType) {
		Result res = new Result();
		if(StringUtil.isEmpty(commetId)){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("评论ID不能为空");
			return res;
		}
		if(StringUtil.isEmpty(userCode)){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("用户账号不能为空");
			return res;
		}
		if(StringUtil.isEmpty(agreeType)){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("点赞类型不能为空");
			return res;
		}
		try{
			JokeAgreeHisModel model = new JokeAgreeHisModel();
			model.setUserCode(userCode);
			model.setCommetId(Integer.valueOf(commetId));
			List<JokeAgreeHisModel> agreeHisList = jokeAgreeHisService.selectAgreeHisList(model);
			if(agreeHisList.size()>0){
				res.setCode(Result.CODE_PARA_ERROR);
				res.setMsg("您已经点过赞了");
				return res;
			}
			int count = jokeCommetService.commetAgree(commetId, userCode, agreeType);
			if(count==2){
				res.setCode(Result.CODE_OK);
			}else{
				res.setCode(Result.CODE_ERROR);
				res.setMsg("点赞失败");
				return res;
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			res.setCode(Result.CODE_ERROR);
			res.setMsg("服务器异常，点赞失败");
		}
		return res;
	}
	
	/**
	 * 获取评论列表json
	 * 
	 * @param jokeText
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCommetList")
	@ResponseBody
	public Result getCommetList(JokeCommetModel jokeCommet) {
		Result res = new Result();
		logger.info("查询评论列表start");
		List<JokeCommetModel> jokeTextList = jokeCommetService.queryCommetList(jokeCommet);
		logger.info("查询评论列表end");
		res.setCode(Result.CODE_OK);
		res.setData(jokeTextList);
		return res;
	}
	
	/**
	 * 获取神评
	 * 
	 * @param jokeText
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getGodCommet")
	@ResponseBody
	public Result getGodCommet(JokeCommetModel jokeCommet) {
		Result res = new Result();
		logger.info("查询神评start");
		JokeCommetModel jokeTextList = jokeCommetService.queryGodCommet(jokeCommet);
		logger.info("查询神评end");
		res.setCode(Result.CODE_OK);
		res.setData(jokeTextList);
		return res;
	}

}
