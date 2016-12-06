package com.shop.base.entity;

public class JokeLastIdModel {
    private Integer id;

    private String jokeType;

    private Integer jokeImgId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJokeType() {
        return jokeType;
    }

    public void setJokeType(String jokeType) {
        this.jokeType = jokeType == null ? null : jokeType.trim();
    }

    public Integer getJokeImgId() {
        return jokeImgId;
    }

    public void setJokeImgId(Integer jokeImgId) {
        this.jokeImgId = jokeImgId;
    }
}