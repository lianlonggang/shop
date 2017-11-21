package com.shop.base.dao;

import java.util.List;

import com.shop.base.entity.JokeImgModel;

public interface JokeImgReviewedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JokeImgModel record);

    int insertSelective(JokeImgModel record);

    JokeImgModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JokeImgModel record);

    int updateByPrimaryKeyWithBLOBs(JokeImgModel record);
    
    public List<JokeImgModel> queryJokeImgNewList(JokeImgModel tblSjsJokeImg);
}