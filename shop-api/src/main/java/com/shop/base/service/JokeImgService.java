package com.shop.base.service;

import java.util.List;

import com.shop.base.entity.JokeImgModel;

public interface JokeImgService {
	int deleteByPrimaryKey(Integer id);

    int insert(JokeImgModel record);

    int insertSelective(JokeImgModel record);

    JokeImgModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JokeImgModel record);

    int updateByPrimaryKeyWithBLOBs(JokeImgModel record);
    
    public List<JokeImgModel> queryJokeImgNewList(JokeImgModel tblSjsJokeImg);
}
