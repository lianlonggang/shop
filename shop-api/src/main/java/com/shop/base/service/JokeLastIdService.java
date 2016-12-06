package com.shop.base.service;

import com.shop.base.entity.JokeLastIdModel;

public interface JokeLastIdService {
	int deleteByPrimaryKey(Integer id);

	int insert(JokeLastIdModel record);

	int insertSelective(JokeLastIdModel record);

	JokeLastIdModel selectByPrimaryKey(Integer id);
	
	JokeLastIdModel findByModel(JokeLastIdModel record);

	int updateByPrimaryKeySelective(JokeLastIdModel record);

	int updateByPrimaryKey(JokeLastIdModel record);
	
	int addLastId(String jokeType);
}
