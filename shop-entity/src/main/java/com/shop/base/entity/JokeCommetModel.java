package com.shop.base.entity;

import java.io.Serializable;
import java.util.Date;

public class JokeCommetModel extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5779886939218630239L;

	private Integer commetId;

	private Integer commetJokeId;

	private Integer userCode;

	private Date commetTime;

	private Integer commetAgreeCount;

	private String commetContent;

	public Integer getCommetId() {
		return commetId;
	}

	public void setCommetId(Integer commetId) {
		this.commetId = commetId;
	}

	public Integer getCommetJokeId() {
		return commetJokeId;
	}

	public void setCommetJokeId(Integer commetJokeId) {
		this.commetJokeId = commetJokeId;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public Date getCommetTime() {
		return commetTime;
	}

	public void setCommetTime(Date commetTime) {
		this.commetTime = commetTime;
	}

	public Integer getCommetAgreeCount() {
		return commetAgreeCount;
	}

	public void setCommetAgreeCount(Integer commetAgreeCount) {
		this.commetAgreeCount = commetAgreeCount;
	}

	public String getCommetContent() {
		return commetContent;
	}

	public void setCommetContent(String commetContent) {
		this.commetContent = commetContent == null ? null : commetContent.trim();
	}
}