package com.shop.base.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.shop.base.convert.Content;
import com.shop.base.entity.JokeAgreeHisModel;
import com.shop.base.entity.JokeCommetModel;
import com.shop.base.entity.JokeImgModel;
import com.shop.base.entity.JokeTextModel;
import com.shop.base.entity.JokeUserModel;
import com.shop.base.model.Result;
import com.shop.base.service.BaseCodeService;
import com.shop.base.service.JokeAgreeHisService;
import com.shop.base.service.JokeCommetService;
import com.shop.base.service.JokeImgService;
import com.shop.base.service.JokeTextService;
import com.shop.base.service.JokeUserService;
import com.shop.base.util.HttpsClient;
import com.shop.base.util.SpringApplicationContext;
import com.shop.base.util.StringUtil;
import com.shop.base.wxuser.AES;

import redis.clients.jedis.tool.JedisTool;

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
	@Reference
	private BaseCodeService baseCodeService;

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
	public Result saveJokeUser(JokeUserModel jokeUser,String encryptedData,String iv,String resCode) {
		Result res = new Result();
		//获取sessionKey
		Map<String , String> params = new HashMap<String,String>();
		params.put("appid", baseCodeService.getCodeByType(Content.BASE_WX_APP_ID));
		params.put("secret", baseCodeService.getCodeByType(Content.BASE_WX_APP_SERCRET));
		params.put("js_code",resCode);
		params.put("grant_type",baseCodeService.getCodeByType(Content.BASE_WX_GRANT_TYPE));
		JSONObject json = HttpsClient.httpsRequest(baseCodeService.getCodeByType(Content.BASE_WX_JTS_URL), "GET", params);
		String session_key = json.getString("session_key");
		String openid = json.getString("openid");
		String sId = SpringApplicationContext.getSessionId();
		JokeUserModel user =  jokeUserService.selectByUserCode(openid);
		try {
			JedisTool.setString(sId, openid, 36000);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(user==null){
			try{
				//解密encryptedData
				AES aes = new AES();
				byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(session_key), Base64.decodeBase64(iv));
				if(null != resultByte && resultByte.length > 0){
					JSONObject userInfo = JSONObject.parseObject(new String(resultByte, "UTF-8"));
					jokeUser.setRegistTime(new Date());
					jokeUser.setUserCode(userInfo.getString("openId"));
					jokeUser.setUserName(userInfo.getString("nickName"));
					jokeUser.setUserGender(userInfo.getString("gender"));
					jokeUser.setUserCity(userInfo.getString("city"));
					jokeUser.setUserProvince(userInfo.getString("province"));
					jokeUser.setUserCountry(userInfo.getString("country"));
					jokeUser.setUserAvatarUrl(userInfo.getString("avatarUrl"));
					int count = jokeUserService.insert(jokeUser);
					if(count==1){
						res.setCode(Result.CODE_OK);
						res.setsId(sId);
					}else{
						res.setCode(Result.CODE_ERROR);
						res.setMsg("保存用户信息失败");
						return res;
					}
				}
				
			}catch(Exception e){
				logger.error(e.getMessage());
				res.setCode(Result.CODE_ERROR);
				res.setMsg("保存用户信息失败");
			}
			return res;
		}else{
			logger.error("已存在该用户");
			res.setCode(Result.CODE_OK);
			res.setsId(sId);
			return res;
		}
		
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
		if(StringUtil.isEmpty(jokeCommet.getsId())){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("sessionID不能为空");
			return res;
		}
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
//		if(StringUtil.isEmpty(jokeCommet.getUserCode())){
//			res.setCode(Result.CODE_PARA_ERROR);
//			res.setMsg("用户账号不能为空");
//			return res;
//		}
		String openId = "";
		try {
			openId = JedisTool.getString(jokeCommet.getsId());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jokeCommet.setUserCode(openId);
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
	public Result commetAgree(String commetId,String agreeType,String sId) {
		Result res = new Result();
		if(StringUtil.isEmpty(sId)){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("sessionID不能为空");
			return res;
		}
		if(StringUtil.isEmpty(commetId)){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("评论ID不能为空");
			return res;
		}
//		if(StringUtil.isEmpty(userCode)){
//			res.setCode(Result.CODE_PARA_ERROR);
//			res.setMsg("用户账号不能为空");
//			return res;
//		}
		if(StringUtil.isEmpty(agreeType)){
			res.setCode(Result.CODE_PARA_ERROR);
			res.setMsg("点赞类型不能为空");
			return res;
		}
		try{
			JokeAgreeHisModel model = new JokeAgreeHisModel();
			String openId = "";
			try {
				openId = JedisTool.getString(sId);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			model.setUserCode(openId);
			model.setCommetId(Integer.valueOf(commetId));
			List<JokeAgreeHisModel> agreeHisList = jokeAgreeHisService.selectAgreeHisList(model);
			if(agreeHisList.size()>0){
				res.setCode(Result.CODE_PARA_ERROR);
				res.setMsg("您已经点过赞了");
				return res;
			}
			int count = jokeCommetService.commetAgree(commetId, openId, agreeType);
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
		List<JokeCommetModel> commetList = jokeCommetService.queryCommetList(jokeCommet);
		logger.info("查询评论列表end");
		res.setCode(Result.CODE_OK);
		res.setData(commetList);
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
		JokeCommetModel godCommet = jokeCommetService.queryGodCommet(jokeCommet);
		logger.info("查询神评end");
		res.setCode(Result.CODE_OK);
		res.setData(godCommet);
		return res;
	}
	
	/**
	 * 获取神评
	 * 
	 * @param 清空缓存
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/codeTypeCacheClear")
	@ResponseBody
	public Result codeTypeCacheClear() {
		Result res = new Result();
		try {
			JedisTool.del("baseCodeMap");
			res.setCode(Result.CODE_OK);
			res.setMsg("清空缓存成功");
			logger.info("清空缓存成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			res.setCode(Result.CODE_OK);
			res.setMsg("清空缓存失败");
		}
		return res;
	}
	
	public static void main(String[] args) {
//		Map<String , String> params = new HashMap<String,String>();
//		params.put("appid", Content.WX_APPID);
//		params.put("secret", Content.WX_SECRET);
//		params.put("js_code","003vPPHL1nJ6y215Z2EL1E8SHL1vPPHw");
//		params.put("grant_type",Content.WX_GRANT_TYPE);
//		//"appid=wx3d05766b984de06e&secret=9ce946c70c47e1eb2e9106cef958072c&js_code=013CuVM31uYUVM1anKO31gxQM31CuVMZ&grant_type=authorization_code"
//		HttpsClient.httpsRequest(Content.WX_JSCODE_TO_SESSION, 
//				"GET", params);
	}

}
