package com.example.cloudx_small_tools.mapper;

import com.example.cloudx_small_tools.po.QueryRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 查询表关联
 * @author: 10191
 * @date:2022/4/20 11:44
 **/
@Mapper
public interface QueryRelationMapper {

    List<QueryRelation> getQueryRelationById(@Param("id") Long id);

    List<QueryRelation> getQueryRelationByIds(@Param("ids") List<Long> ids);

    List<QueryRelation> getQueryRelationByBaseTable(@Param("baseTable") String baseTable);
}
