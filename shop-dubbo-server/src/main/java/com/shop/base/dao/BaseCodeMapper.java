package com.shop.base.dao;

import java.util.List;

import com.shop.base.entity.BaseCodeModel;

public interface BaseCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseCodeModel record);

    int insertSelective(BaseCodeModel record);

    BaseCodeModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseCodeModel record);

    int updateByPrimaryKey(BaseCodeModel record);
    
    List<BaseCodeModel> findList(BaseCodeModel record);
    
    String transCode(String type,String code);
    
    String getCodeByType(String type);
}