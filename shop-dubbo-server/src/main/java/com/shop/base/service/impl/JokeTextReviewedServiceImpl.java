package com.shop.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeTextReviewedMapper;
import com.shop.base.entity.JokeTextModel;
import com.shop.base.service.JokeTextReviewedService;

@Service
public class JokeTextReviewedServiceImpl implements JokeTextReviewedService {

	@Autowired  
	private JokeTextReviewedMapper jokeTextReviewedMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return jokeTextReviewedMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(JokeTextModel record) {
		return jokeTextReviewedMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeTextModel record) {
		return jokeTextReviewedMapper.insertSelective(record);
	}

	@Override
	public JokeTextModel selectByPrimaryKey(Integer id) {
		return jokeTextReviewedMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeTextModel record) {
		return jokeTextReviewedMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(JokeTextModel record) {
		return jokeTextReviewedMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public List<JokeTextModel> queryJokeTextNewList(JokeTextModel tblSjsJokeText) {
		return jokeTextReviewedMapper.queryJokeTextNewList(tblSjsJokeText);
	}

}
