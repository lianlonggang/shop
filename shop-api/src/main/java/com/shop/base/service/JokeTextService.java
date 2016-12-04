package com.shop.base.service;

import java.util.List;

import com.shop.base.entity.JokeTextModel;

public interface JokeTextService {
	int deleteByPrimaryKey(Integer id);

	int insert(JokeTextModel record);

	int insertSelective(JokeTextModel record);

	JokeTextModel selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(JokeTextModel record);

	int updateByPrimaryKeyWithBLOBs(JokeTextModel record);

	public List<JokeTextModel> queryJokeTextNewList(JokeTextModel tblSjsJokeText);
}
