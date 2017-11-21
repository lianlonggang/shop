package com.shop.base.dao;

import java.util.List;

import com.shop.base.entity.JokeTextModel;

public interface JokeTextReviewedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JokeTextModel record);

    int insertSelective(JokeTextModel record);

    JokeTextModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JokeTextModel record);

    int updateByPrimaryKeyWithBLOBs(JokeTextModel record);
    
    public List<JokeTextModel> queryJokeTextNewList(JokeTextModel tblSjsJokeText);
}