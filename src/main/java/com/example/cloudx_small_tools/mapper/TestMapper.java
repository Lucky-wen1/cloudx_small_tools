package com.example.cloudx_small_tools.mapper;

import com.example.cloudx_small_tools.po.QueryConfiguration;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 测试
 * @author: 10191
 * @date:2022/4/16 17:19
 **/
@Mapper
public interface TestMapper {

    List<QueryConfiguration> getQuery();
}
