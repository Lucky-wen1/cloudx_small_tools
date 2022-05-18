package com.example.cloudx_small_tools.service;
import com.example.cloudx_small_tools.po.QueryConfiguration;
import com.example.cloudx_small_tools.po.QueryRelation;
import com.example.cloudx_small_tools.vo.QueryVO;

import java.util.List;

/**
 * @Description: SQL 生成服务
 * @author: 10191
 * @date:2022/4/19 17:07
 **/
public interface QuerySqlService {

    /**
     * 查询SQL生成
     * @param queryVOList
     * @return
     */
    String getQuerySql(List<QueryVO> queryVOList);

    /**
     * 根据表查询字段配置信息
     * @param table
     * @return
     */
    List<QueryConfiguration> getQueryConfig(String table);

    /**
     * 根据表查询表关联信息
     * @param table
     * @return
     */
    List<QueryRelation> getQueryRelation(String table);
}


