package com.shop.base.util;

import java.util.ArrayList;
import java.util.List;

public class Contants {
	public static final String APPMD5="u";
	public static final String ENCRYPTTYPE_RSA="rsa";
	public static final String CHARSET="UTF-8";
	public static final String BASE_STATE="0";//可用状态
	public static final String BASE_STATE_DIS="1";//不可用状态
	
	public static final String COOKIE_PATH ="/";
	public static final String COOKIE_NAME ="JSESSIONID";
	public static final String SESSION_ID ="SID";
	
	public static final String SESSION_USERID = "USER";
	public static final String DISABLE_STATUS = "1"; //状态不可用
	public static final String ENABLE_STATUS = "0"; //状态可用
	
	/**
	 * 菜单类型   moudle模块  menu菜单  fun按钮 link 链接
	 */
	public static final String BASE_FUNCTION_TYPE_MENU="menu";
	public static final String BASE_FUNCTION_TYPE_MOUDLE="moudle";
	public static final String BASE_FUNCTION_TYPE_BTN="button.*";
	public static final String BASE_FUNCTION_TYPE_LINK="link.*";
	
	/**
	 * FTP 文件请求前缀
	 * */
	public static final String FTP_DOWNLOAD_URL_PREFIX = "/download/file";
	
	/**
	 * 菜单缓存ID
	 */
	public static final String CACHE_FUN_LIST="fun_list";
	/**
	 * 正则表达式对应菜单缓存id
	 */
	public static final String CACHE_REGEXP_FUN_MAP="regexp_fun_map";
	/**
	 * 角色对应菜单缓存的id  
	 */
	public static final String CACHE_ROLE_FUN_MAP="role_fun_map";
	/**
	 * 角色对应菜单缓存的id  
	 */
	public static final String CACHE_URL_ROLE_MAP="url_role_map";
	
	/**
	 * 收费项目类型-周期性
	 */
	public static final String PRO_ITEMTYPE_CYCLIC="0";
	/**
	 * 收费项目类型-非周期性
	 */
	public static final String PRO_ITEMTYPE_NOCYCLIC="1";
	/**
	 * 收费项目-是关键项
	 */
	public static final String PRO_ITEM_IS_KEY_Y = "0";
	/**
	 * 收费项目-不是关键项
	 */
	public static final String PRO_ITEM_IS_KEY_N = "1";
	
	public static final String CACHE_LOGIN_IMGCODE="IMG";
	public static final String CACHE_LOGIN_TIME="TIME";
	public static final String CACHE_LOGIN_SMSCODE="SMS";
	public static final String CACHE_LOGIN_PROCOMMNITY="PROCOMMNITY";
	public static final String CACHE_LOGIN_SHOP="shop";
	
	public static final List<String> CACHE_SESSIONID_LIST = new ArrayList<String>();
	static{
		CACHE_SESSIONID_LIST.add(CACHE_LOGIN_IMGCODE);
	}
	public static final String BASE_SQL_CACHE_KEY="sql";
	/**
	 * 系统用户类型
	 */
	public static final String USER_TYPE_SYS="sys";
	/**
	 * 通知类型
	 */
	public static final String NOTICE_TYPE_SYSTEM="system";
	public static final String NOTICE_TYPE_DEPARTMENT="department";
	public static final String NOTICE_TYPE_COMMUNITY="community";
	/**
	 * 物业用户类型
	 */
	public static final String USER_TYPE_PRO="pro";
	public static final String CURRENT_USER_TYPE = "currentUserType";
	public static final String CURRENT_USER_ID = "currentUser";
	public static final String CURRENT_SYS_TYPE = "currentSysType";
	/**
	 * 自动模板 form表单类型
	 */
	public static final String FORM_SHOWTYPE_HIDDEN="hidden";//隐藏域
	public static final String FORM_SHOWTYPE_TEXT="text";//文本
	public static final String FORM_SHOWTYPE_INTEGER="integer";//数字
	public static final String FORM_SHOWTYPE_NUMBER="number";//浮点数
	public static final String FORM_SHOWTYPE_SELECT="select";//下拉框
	public static final String FORM_SHOWTYPE_MSELECT="mselect";//多选下拉框
	public static final String FORM_SHOWTYPE_TREE="tree";//单选树
	public static final String FORM_SHOWTYPE_MTREE="mtree";//多选树
	public static final String FORM_SHOWTYPE_TEXTAREA="textarea";//多行文本
	public static final String FORM_SHOWTYPE_FILE="file";//多行文本
	public static final String FORM_SHOWTYPE_DATEBOX="datebox";//日期 年月日
	public static final String FORM_SHOWTYPE_DATETIMEBOX="datetimebox";//日期 年月日 时分秒
	/**
	 * 自动模板form校验类型
	 */
	public static final String FORM_CHECKTYPE_UNIQUE="unique";//唯一
	public static final String FORM_CHECKTYPE_MOBILE="mobile";//手机号
	public static final String FORM_CHECKTYPE_NOTNULL="notNull";//非空
	public static final String FORM_CHECKTYPE_IDCARD="idcard";//身份证
	public static final String FORM_CHECKTYPE_INTEGER="integer";//整型数字
	public static final String FORM_CHECKTYPE_NUMBER="number";//浮点型数字
	public static final String FORM_CHECKTYPE_EMAIL="email";//浮点型数字
	//对应数据库 超级管理员类型
	public static final String SUPER_USER_TYPE = "6";
	public static final String PUBLIC_ROLETYPE = "public";
	/**
	 *form表单 主表 字表 key 
	 */
	public static final String FORM_KEY_MAIN="main";//存放主表的key
	public static final String FORM_KEY_SUBS="subs";//存放子表的key
	public static final String FORM_KEY_VIEW="view";//存放已提交信息的key
	public static final String FORM_KEY_INFO="info";//存放信息的key
	public static final String FORM_KEY_BUSENESSID="busenessId";//存放业务id
	public static final String FORM_KEY_PKNAME="pkName";//存放业务表的主键名称
	public static final String FORM_KEY_DATA="data";//存放业务数据
	public static final String FORM_KEY_DATA_SQL="datasql";//存放业务数据sql
	public static final String FORM_KEY_KEY="key";//存放业务KEY
	public static final String FORM_KEY_NODEID="processNodeId";//存放业务KEY下的节点id
	public static final String FORM_KEY_BTNS="btns";//存放业务表单的按钮集合
	public static final String FORM_KEY_TAB="tab";//存放业务表  表的信息
	public static final String FORM_KEY_COLS="cols";//存放业务表 列的信息
	public static final String FORM_KEY_PROCESSINSTANCEID="processInstanceId";//存放业务表  业务实例id
	public static final String FORM_KEY_TASKID="taskId";//存放业务表  任务id
	public static final String FORM_KEY_TABLE_NAME="tablekey";//存放查询信息的表名称
	public static final String FORM_KEY_TABLE_NAME_CH="tablename";//存放查询信息的表名称
	public static final String FORM_KEY_TABLE_ISMORE="ismore";//存放查询信息的表名称
	public static final String FORM_KEY_TABLE_WHERE="tableWhere";//存放查询信息的条件列明
	public static final String FORM_KEY_ACTION="action";//当前节点执行动作
	
	//表单条件运算符
	public static final String FORM_OPERATOR_LIKE = "like";//模糊比较
	
	
	//工作流：创建表默认的表前缀
	public static final String TABLE_FIRST_NAME="ACX_";
	public static final String FORM_OPER_UPD="upd";
	public static final String FORM_OPER_INS="ins";
	public static final String FORM_PROCESS_START="PROCESS_START";//流程启动节点
	public static final String FORM_PROCESS_END="PROCESS_END";//流程结束节点
	public static final String FORM_PROCESS_STARTNAME="开始";//流程启动节点
	public static final String FORM_PROCESS_MAIN_TASKID="00000000";//流程启动主表taskId
	public static final String FORM_PROCESS_USER_ID="userId";//流程启动人的key
	
	
	/**
	 * 自动模版form oracle数据库字段类型
	 */
	public static final String FORM_COL_TYPE_VARCHAR="VARCHAR2";//字符型
	public static final String FORM_COL_TYPE_NUMBER="NUMBER";//小数
	public static final String FORM_COL_TYPE_INTEGER="INTEGER";//整数
	public static final String FORM_COL_TYPE_DATE="DATE";//日期
	
	
	/**
	 * 工作流状态常量
	 */
	public static final String PROCESS_STATUS_IN = "0";//流程中
	public static final String PROCESS_STATUS_END = "1";//完结
	public static final String PROCESS_STATUS_HANGUP = "2";//挂起
	/**
	 * 订单状态
	 */
	public static final String PAY_ORDER_PRO = "PRO";//物业订单
	public static final String PAY_ORDER_ECS = "ECS";//电商订单
	/**
	 * 支付方式
	 */
	public static final String PAYTYPE_CACH = "CASH";//现金
	public static final String PAYTYPE_POS = "POS";//POS机
	public static final String PAYTYPE_SMK = "SMK";//市民卡
	public static final String PAYTYPE_ZFB = "ZFB";//支付宝
	public static final String PAYTYPE_ZZZD = "ZZZD";//自助终端
	/**
	 * 支付状态
	 */
	public static final String PAY_STATUS_SUC = "0";//已支付
	public static final String PAY_STATUS_WAIT = "1";//待支付
	public static final String PAY_STATUS_ERR = "2";//其他
	
	/**
	 * 人员类型
	 */
	public static final String PRO_PERSONTYPE_0 = "persontype_0";//租客
	public static final String PRO_PERSONTYPE_1 = "persontype_1";//业主
	public static final String PRO_PERSONTYPE_2 = "persontype_2";//家庭成员
	public static final String PRO_PERSONTYPE_3 = "persontype_3";//其他人员
	
	public static final String PRO_COMP_MERCHANT_DEFAULT = "S500000001";//北科维拓默认商户ID
}
