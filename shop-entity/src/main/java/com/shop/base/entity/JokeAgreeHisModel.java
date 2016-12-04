package com.shop.base.entity;

import java.io.Serializable;
import java.util.Date;

public class JokeAgreeHisModel extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7028052026852432951L;

	private Integer agreeHisId;

    private Integer commetId;

    private String userCode;

    private Date agreeTime;

    private String agreeType;

    public Integer getAgreeHisId() {
        return agreeHisId;
    }

    public void setAgreeHisId(Integer agreeHisId) {
        this.agreeHisId = agreeHisId;
    }

    public Integer getCommetId() {
        return commetId;
    }

    public void setCommetId(Integer commetId) {
        this.commetId = commetId;
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