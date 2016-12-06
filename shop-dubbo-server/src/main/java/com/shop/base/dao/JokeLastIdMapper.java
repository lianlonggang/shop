package com.shop.base.dao;

import org.apache.ibatis.annotations.Param;

import com.shop.base.entity.JokeLastIdModel;

public interface JokeLastIdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JokeLastIdModel record);

    int insertSelective(JokeLastIdModel record);

    JokeLastIdModel selectByPrimaryKey(Integer id);
    
    JokeLastIdModel findByModel(JokeLastIdModel record);

    int updateByPrimaryKeySelective(JokeLastIdModel record);

    int updateByPrimaryKey(JokeLastIdModel record);
    
    int addLastId(@Param(value="jokeType")String jokeType);
}