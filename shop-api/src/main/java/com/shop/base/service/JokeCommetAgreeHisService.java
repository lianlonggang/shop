package com.shop.base.service;

import java.util.List;

import com.shop.base.entity.JokeCommetAgreeHisModel;

public interface JokeCommetAgreeHisService {
	int deleteByPrimaryKey(Integer agreeHisId);

	int insert(JokeCommetAgreeHisModel record);

	int insertSelective(JokeCommetAgreeHisModel record);

	JokeCommetAgreeHisModel selectByPrimaryKey(Integer agreeHisId);
	
	List<JokeCommetAgreeHisModel> selectCommetAgreeHisList(JokeCommetAgreeHisModel record);

	int updateByPrimaryKeySelective(JokeCommetAgreeHisModel record);

	int updateByPrimaryKey(JokeCommetAgreeHisModel record);
}
