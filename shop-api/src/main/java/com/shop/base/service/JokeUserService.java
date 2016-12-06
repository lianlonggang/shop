package com.shop.base.service;

import com.shop.base.entity.JokeUserModel;

public interface JokeUserService {
	int deleteByPrimaryKey(Integer userId);

	int insert(JokeUserModel record);

	int insertSelective(JokeUserModel record);

	JokeUserModel selectByPrimaryKey(Integer userId);
	
	JokeUserModel selectByUserCode(String userCode);

	int updateByPrimaryKeySelective(JokeUserModel record);

	int updateByPrimaryKey(JokeUserModel record);
}
