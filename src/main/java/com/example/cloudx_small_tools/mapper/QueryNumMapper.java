package com.example.cloudx_small_tools.mapper;

import com.example.cloudx_small_tools.po.QueryNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 查询枚举
 * @author: 10191
 * @date:2022/4/20 10:49
 **/
@Mapper
public interface QueryNumMapper {

    List<QueryNum> getQueryNumByNumId(@Param("numId") Long numId);
}
