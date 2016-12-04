package com.shop.base.model;

import java.io.Serializable;
import java.util.List;

public class DataGrid<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7951450290572845793L;
	private Integer total=0;
	private List<T> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
