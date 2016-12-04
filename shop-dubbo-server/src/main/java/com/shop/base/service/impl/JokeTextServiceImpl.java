package com.shop.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeTextMapper;
import com.shop.base.entity.JokeTextModel;
import com.shop.base.service.JokeTextService;

@Service
public class JokeTextServiceImpl implements JokeTextService {

	@Autowired  
	private JokeTextMapper jokeTextMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return jokeTextMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(JokeTextModel record) {
		return jokeTextMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeTextModel record) {
		return jokeTextMapper.insertSelective(record);
	}

	@Override
	public JokeTextModel selectByPrimaryKey(Integer id) {
		return jokeTextMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeTextModel record) {
		return jokeTextMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(JokeTextModel record) {
		return jokeTextMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public List<JokeTextModel> queryJokeTextNewList(JokeTextModel tblSjsJokeText) {
		return jokeTextMapper.queryJokeTextNewList(tblSjsJokeText);
	}

}
