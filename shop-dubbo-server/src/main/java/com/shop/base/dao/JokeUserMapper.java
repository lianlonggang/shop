package com.shop.base.dao;

import com.shop.base.entity.JokeUserModel;

public interface JokeUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(JokeUserModel record);

    int insertSelective(JokeUserModel record);

    JokeUserModel selectByPrimaryKey(Integer userId);
    
    JokeUserModel selectByUserCode(String userCode);

    int updateByPrimaryKeySelective(JokeUserModel record);

    int updateByPrimaryKey(JokeUserModel record);
}