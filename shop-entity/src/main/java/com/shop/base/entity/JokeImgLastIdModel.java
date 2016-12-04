package com.shop.base.entity;

import java.io.Serializable;

public class JokeImgLastIdModel extends BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5660707835278453606L;

	private Integer id;

	private Integer jokeImgId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJokeImgId() {
		return jokeImgId;
	}

	public void setJokeImgId(Integer jokeImgId) {
		this.jokeImgId = jokeImgId;
	}
}