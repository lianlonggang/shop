package com.shop.base.service;

import com.shop.base.entity.JokeImgLastIdModel;

public interface JokeImgLastIdService {
	int deleteByPrimaryKey(Integer id);

	int insert(JokeImgLastIdModel record);

	int insertSelective(JokeImgLastIdModel record);

	JokeImgLastIdModel selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(JokeImgLastIdModel record);

	int updateByPrimaryKey(JokeImgLastIdModel record);
}
