package com.shop.base.entity;

import java.io.Serializable;
import java.util.Date;

public class JokeAgreeHisModel extends BaseModel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 9220447558338810129L;

	private Integer agreeHisId;

    private Integer jokeId;

    private String jokeType;

    private String userCode;

    private Date agreeTime;

    private String agreeType;

    public Integer getAgreeHisId() {
        return agreeHisId;
    }

    public void setAgreeHisId(Integer agreeHisId) {
        this.agreeHisId = agreeHisId;
    }

    public Integer getJokeId() {
        return jokeId;
    }

    public void setJokeId(Integer jokeId) {
        this.jokeId = jokeId;
    }

    public String getJokeType() {
        return jokeType;
    }

    public void setJokeType(String jokeType) {
        this.jokeType = jokeType == null ? null : jokeType.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Date getAgreeTime() {
        return agreeTime;
    }

    public void setAgreeTime(Date agreeTime) {
        this.agreeTime = agreeTime;
    }

    public String getAgreeType() {
        return agreeType;
    }

    public void setAgreeType(String agreeType) {
        this.agreeType = agreeType == null ? null : agreeType.trim();
    }
}