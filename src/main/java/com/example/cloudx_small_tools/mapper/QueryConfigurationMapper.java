package com.example.cloudx_small_tools.mapper;

import com.example.cloudx_small_tools.po.QueryConfiguration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 查询字段配置
 * @author: 10191
 * @date:2022/4/19 17:13
 **/
@Mapper
public interface QueryConfigurationMapper {

    List<QueryConfiguration> getQueryConfigById(@Param("id") Long id);

    List<QueryConfiguration> getQueryConfigByIds(@Param("ids") List<Long> ids);

    List<QueryConfiguration> getQueryConfigByTable(@Param("queryTable") String queryTable);
}
