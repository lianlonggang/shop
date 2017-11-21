package com.shop.base.service;

import java.util.List;

import com.shop.base.entity.JokeCommetModel;

public interface JokeCommetService {
	int deleteByPrimaryKey(Integer commetId);

	int insert(JokeCommetModel record);

	int insertSelective(JokeCommetModel record);

	JokeCommetModel selectByPrimaryKey(Integer commetId);

	List<JokeCommetModel> queryCommetList(JokeCommetModel record);

	JokeCommetModel queryGodCommet(JokeCommetModel record);

	int updateByPrimaryKeySelective(JokeCommetModel record);

	int updateByPrimaryKeyWithBLOBs(JokeCommetModel record);

	int updateByPrimaryKey(JokeCommetModel record);

	// 点赞
	int commetAgree(String commetId, String userCode, String agreeType);
	// 取消点赞
	int disCommetAgree(String commetId, String userCode,String agreeType);
}
