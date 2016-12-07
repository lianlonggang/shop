package com.shop.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseModel {
	private Integer pageNo = 1;

	@SuppressWarnings("unused")
	private Integer rowNo = 0;
	private String sId = "";
	private Integer pageSize = 10;
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
	
	
}
