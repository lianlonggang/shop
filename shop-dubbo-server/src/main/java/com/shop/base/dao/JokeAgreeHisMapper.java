package com.shop.base.dao;

import java.util.List;

import com.shop.base.entity.JokeAgreeHisModel;

public interface JokeAgreeHisMapper {
    int deleteByPrimaryKey(Integer agreeHisId);

    int insert(JokeAgreeHisModel record);

    int insertSelective(JokeAgreeHisModel record);

    JokeAgreeHisModel selectByPrimaryKey(Integer agreeHisId);
    
    List<JokeAgreeHisModel> selectAgreeHisList(JokeAgreeHisModel record);

    int updateByPrimaryKeySelective(JokeAgreeHisModel record);

    int updateByPrimaryKey(JokeAgreeHisModel record);
}