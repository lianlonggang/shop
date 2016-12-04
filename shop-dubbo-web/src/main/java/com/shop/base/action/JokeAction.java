package com.shop.base.action;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shop.base.entity.JokeImgModel;
import com.shop.base.entity.JokeTextModel;
import com.shop.base.entity.JokeUserModel;
import com.shop.base.model.Result;
import com.shop.base.service.JokeImgService;
import com.shop.base.service.JokeTextService;
import com.shop.base.service.JokeUserService;

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
		Integer pageNo = jokeImg.getPageNo() == null ? 1 : jokeImg.getPageNo();
		Integer pageSize = jokeImg.getPageSize() == null ? 10 : jokeImg.getPageSize();
		Integer rowNo = (pageNo - 1) * pageSize;
		jokeImg.setRowNo(rowNo);
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
		Integer pageNo = jokeText.getPageNo() == null ? 1 : jokeText.getPageNo();
		Integer pageSize = jokeText.getPageSize() == null ? 10 : jokeText.getPageSize();
		Integer rowNo = (pageNo - 1) * pageSize;
		jokeText.setRowNo(rowNo);
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
			jokeUserService.insert(jokeUser);
			res.setCode(Result.CODE_OK);
		}catch(Exception e){
			res.setCode(Result.CODE_ERROR);
			res.setMsg(e.getMessage());
		}
		return res;
	}
	
	

}
