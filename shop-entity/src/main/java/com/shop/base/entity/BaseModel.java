package com.shop.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseModel {
	private Integer pageNo = 1;

	@SuppressWarnings("unused")
	private Integer rowNo = 0;
	private String sId = "";
	private Integer pageSize = 10;
	private Integer agreeCount;
	private String iconAgreeFlag;
	private String iconDisagreeFlag;
	private String iconCommetAgreeFlag;
	private String iconCommetDisagreeFlag;
	private String paramUserCode;
	private Integer disagreeCount;
	private Integer commetCount;
	private String commonUserImg;
	private String commonUserName;

	@JsonIgnore
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@JsonIgnore
	public Integer getRowNo() {
		return (getPageNo() - 1) * getPageSize();
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}

	@JsonIgnore
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@JsonIgnore
	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public Integer getAgreeCount() {
		return agreeCount;
	}

	public void setAgreeCount(Integer agreeCount) {
		this.agreeCount = agreeCount;
	}

	public Integer getDisagreeCount() {
		return disagreeCount;
	}

	public void setDisagreeCount(Integer disagreeCount) {
		this.disagreeCount = disagreeCount;
	}

	public Integer getCommetCount() {
		return commetCount;
	}

	public void setCommetCount(Integer commetCount) {
		this.commetCount = commetCount;
	}

	public String getCommonUserImg() {
		return commonUserImg;
	}

	public void setCommonUserImg(String commonUserImg) {
		this.commonUserImg = commonUserImg;
	}

	public String getCommonUserName() {
		return commonUserName;
	}

	public void setCommonUserName(String commonUserName) {
		this.commonUserName = commonUserName;
	}

	public String getIconAgreeFlag() {
		return iconAgreeFlag;
	}

	public void setIconAgreeFlag(String iconAgreeFlag) {
		this.iconAgreeFlag = iconAgreeFlag;
	}

	public String getIconDisagreeFlag() {
		return iconDisagreeFlag;
	}

	public void setIconDisagreeFlag(String iconDisagreeFlag) {
		this.iconDisagreeFlag = iconDisagreeFlag;
	}

	public String getParamUserCode() {
		return paramUserCode;
	}

	public void setParamUserCode(String paramUserCode) {
		this.paramUserCode = paramUserCode;
	}

	public String getIconCommetAgreeFlag() {
		return iconCommetAgreeFlag;
	}

	public void setIconCommetAgreeFlag(String iconCommetAgreeFlag) {
		this.iconCommetAgreeFlag = iconCommetAgreeFlag;
	}

	public String getIconCommetDisagreeFlag() {
		return iconCommetDisagreeFlag;
	}

	public void setIconCommetDisagreeFlag(String iconCommetDisagreeFlag) {
		this.iconCommetDisagreeFlag = iconCommetDisagreeFlag;
	}

}
