package com.shop.base.dao;

import java.util.List;

import com.shop.base.entity.JokeCommetModel;

public interface JokeCommetMapper {
    int deleteByPrimaryKey(Integer commetId);

    int insert(JokeCommetModel record);

    int insertSelective(JokeCommetModel record);

    JokeCommetModel selectByPrimaryKey(Integer commetId);
    
    List<JokeCommetModel> queryCommetList(JokeCommetModel record);
    
    JokeCommetModel queryGodCommet(JokeCommetModel record);

    int updateByPrimaryKeySelective(JokeCommetModel record);

    int updateByPrimaryKeyWithBLOBs(JokeCommetModel record);

    int updateByPrimaryKey(JokeCommetModel record);
    
    int addAgreeCount(JokeCommetModel record);
    
    int addDisagreeCount(JokeCommetModel record);
    
    int minuteAgreeCount(JokeCommetModel record);
    
    int minuteDisagreeCount(JokeCommetModel record);
}