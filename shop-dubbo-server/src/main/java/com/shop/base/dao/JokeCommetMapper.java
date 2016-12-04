package com.shop.base.dao;

import com.shop.base.entity.JokeCommetModel;

public interface JokeCommetMapper {
    int deleteByPrimaryKey(Integer commetId);

    int insert(JokeCommetModel record);

    int insertSelective(JokeCommetModel record);

    JokeCommetModel selectByPrimaryKey(Integer commetId);

    int updateByPrimaryKeySelective(JokeCommetModel record);

    int updateByPrimaryKeyWithBLOBs(JokeCommetModel record);

    int updateByPrimaryKey(JokeCommetModel record);
}