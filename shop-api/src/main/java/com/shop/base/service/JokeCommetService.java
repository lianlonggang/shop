package com.shop.base.service;

import com.shop.base.entity.JokeCommetModel;

public interface JokeCommetService {
	int deleteByPrimaryKey(Integer commetId);

	int insert(JokeCommetModel record);

	int insertSelective(JokeCommetModel record);

	JokeCommetModel selectByPrimaryKey(Integer commetId);

	int updateByPrimaryKeySelective(JokeCommetModel record);

	int updateByPrimaryKeyWithBLOBs(JokeCommetModel record);

	int updateByPrimaryKey(JokeCommetModel record);
}
