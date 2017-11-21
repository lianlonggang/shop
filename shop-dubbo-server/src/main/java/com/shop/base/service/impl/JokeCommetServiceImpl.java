package com.shop.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shop.base.dao.JokeCommetAgreeHisMapper;
import com.shop.base.dao.JokeCommetMapper;
import com.shop.base.entity.JokeCommetAgreeHisModel;
import com.shop.base.entity.JokeCommetModel;
import com.shop.base.service.JokeCommetService;

@Service
public class JokeCommetServiceImpl implements JokeCommetService {

	@Autowired
	private JokeCommetMapper jokeCommetMapper;
	@Autowired
	private JokeCommetAgreeHisMapper jokeAgreeHisMapper;

	@Override
	public int deleteByPrimaryKey(Integer commetId) {
		return jokeCommetMapper.deleteByPrimaryKey(commetId);
	}

	@Override
	public int insert(JokeCommetModel record) {
		return jokeCommetMapper.insert(record);
	}

	@Override
	public List<JokeCommetModel> queryCommetList(JokeCommetModel record) {
		return jokeCommetMapper.queryCommetList(record);
	}

	@Override
	public JokeCommetModel queryGodCommet(JokeCommetModel record) {
		return jokeCommetMapper.queryGodCommet(record);
	}

	@Override
	public int insertSelective(JokeCommetModel record) {
		return jokeCommetMapper.insertSelective(record);
	}

	@Override
	public JokeCommetModel selectByPrimaryKey(Integer commetId) {
		return jokeCommetMapper.selectByPrimaryKey(commetId);
	}

	@Override
	public int updateByPrimaryKeySelective(JokeCommetModel record) {
		return jokeCommetMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(JokeCommetModel record) {
		return jokeCommetMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(JokeCommetModel record) {
		return jokeCommetMapper.updateByPrimaryKey(record);
	}

	@Override
	public int commetAgree(String commetId, String userCode,String agreeType) {
		int count = -1;
		JokeCommetModel record = new JokeCommetModel();
		record.setCommetId(Integer.valueOf(commetId));
		if("disagree".equalsIgnoreCase(agreeType)){
			count = jokeCommetMapper.addDisagreeCount(record);
		}else{
			count = jokeCommetMapper.addAgreeCount(record);
		}
		JokeCommetAgreeHisModel agreeHis = new JokeCommetAgreeHisModel();
		agreeHis.setCommetId(Integer.valueOf(commetId));
		agreeHis.setUserCode(userCode);
		agreeHis.setAgreeType(agreeType);
		agreeHis.setAgreeTime(new Date());
		count += jokeAgreeHisMapper.insert(agreeHis);
		
		return count;
	}
	@Override
	public int disCommetAgree(String commetId, String userCode,String agreeType) {
		int count = -1;
		JokeCommetModel record = new JokeCommetModel();
		record.setCommetId(Integer.valueOf(commetId));
		if("disagree".equalsIgnoreCase(agreeType)){
			count = jokeCommetMapper.minuteDisagreeCount(record);
		}else{
			count = jokeCommetMapper.minuteAgreeCount(record);
		}
		JokeCommetAgreeHisModel agreeHis = new JokeCommetAgreeHisModel();
		agreeHis.setCommetId(Integer.valueOf(commetId));
		agreeHis.setUserCode(userCode);
		agreeHis.setAgreeType(agreeType);
		List<JokeCommetAgreeHisModel> jahList = jokeAgreeHisMapper.selectCommetAgreeHisList(agreeHis);
		if(jahList!=null && jahList.size()>0){
			count += jokeAgreeHisMapper.deleteByPrimaryKey(jahList.get(0).getAgreeHisId());
		}
		
		return count;
	}

}
