package com.shop.base.entity;

import java.io.Serializable;
import java.util.Date;

public class JokeCommetModel extends BaseModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 584751744877589602L;

	private Integer commetId;

    private Integer commetParntId;

    private Integer commetJokeId;

    private String commetJokeType;

    private String userCode;

    private Date commetTime;

    private Integer commetAgreeCount;

    private Integer commetDisagreeCount;

    private String commetContent;

    public Integer getCommetId() {
        return commetId;
    }

    public void setCommetId(Integer commetId) {
        this.commetId = commetId;
    }

    public Integer getCommetParntId() {
        return commetParntId;
    }

    public void setCommetParntId(Integer commetParntId) {
        this.commetParntId = commetParntId;
    }

    public Integer getCommetJokeId() {
        return commetJokeId;
    }

    public void setCommetJokeId(Integer commetJokeId) {
        this.commetJokeId = commetJokeId;
    }

    public String getCommetJokeType() {
        return commetJokeType;
    }

    public void setCommetJokeType(String commetJokeType) {
        this.commetJokeType = commetJokeType == null ? null : commetJokeType.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
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

    public Integer getCommetDisagreeCount() {
        return commetDisagreeCount;
    }

    public void setCommetDisagreeCount(Integer commetDisagreeCount) {
        this.commetDisagreeCount = commetDisagreeCount;
    }

    public String getCommetContent() {
        return commetContent;
    }

    public void setCommetContent(String commetContent) {
        this.commetContent = commetContent == null ? null : commetContent.trim();
    }
}