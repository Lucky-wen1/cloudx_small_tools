package com.example.cloudx_small_tools.service;
import com.example.cloudx_small_tools.po.QueryConfiguration;
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
     * @param queryVO
     * @return
     */
    String getQuerySql(QueryVO queryVO);
}


