package com.shop.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseModel {
	private Integer pageNo = 1;

	private Integer rowNo = 0;

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
		return rowNo;
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
	
	
}
