package com.shop.base.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Result  implements Serializable{
	 /**
    * 未知错误
    */
   public static final int CODE_UNKNOWN = -1;
   /**
    * 成功
    */
   public static final int CODE_OK = 0;
   /**
    * 成功（无数据）
    */
   public static final int CODE_NO_DATA = 1;
   /**
    * 传入参数错误
    */
   public static final int CODE_PARA_ERROR = 2;
   /**
    * 需删除下级数据
    */
   public static final int CODE_CHILD_ERROR = 3;
   /**
    * 错误（后台错误）
    */
   public static final int CODE_ERROR = 10;
   /**
    * 需要登录
    */
   public static final int CODE_LOGIN_REQUIRE = 11;
   /**
    * 无权限
    */
   public static final int CODE_NO_RIGHT = 12;
   /**
    * 无权限
    */
   public static final int CODE_NEED_JUMP = 13;
   
	private int code =CODE_UNKNOWN;
	private static final long serialVersionUID = 1000000005L;
	private String msg;//信息的描述
	private Object data;//返回的数据
	private String dateTime;//返回的时间
	private String msgId;//流水号
	private String sId;
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		if(msg==null){
			return "";
		}
		return msg;
	}
	public void setMsg(String msg) {
		if(msg!=null&&(msg.trim().endsWith("!")||msg.trim().endsWith("！")||"".equals(msg))){
			this.msg = msg;
		}else{
			this.msg = msg + "!";
		}
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		if(dateTime==null){
			this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			return ;
		}
		this.dateTime = dateTime;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	
}
