package com.shop.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeImgReviewedMapper;
import com.shop.base.entity.JokeImgModel;
import com.shop.base.service.JokeImgReviewedService;

@Service
public class JokeImgReviewedServiceImpl implements JokeImgReviewedService {

	@Autowired  
	private JokeImgReviewedMapper jokeImgReviewedMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return jokeImgReviewedMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(JokeImgModel record) {
		return jokeImgReviewedMapper.insert(record);
	}

	@Override
	public int insertSelective(JokeImgModel record) {
		return jokeImgReviewedMapper.insertSelective(record);
	}

	@Override
	public JokeImgModel selectByPrimaryKey(Integer id) {
		return jokeImgReviewedMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeImgModel record) {
		return jokeImgReviewedMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(JokeImgModel record) {
		return jokeImgReviewedMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public List<JokeImgModel> queryJokeImgNewList(JokeImgModel tblSjsJokeImg) {
		return jokeImgReviewedMapper.queryJokeImgNewList(tblSjsJokeImg);
	}

}
