package com.example.cloudx_small_tools.controller;
import com.example.cloudx_small_tools.mapper.TestMapper;
import com.example.cloudx_small_tools.po.QueryConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: TODO
 * @author: 10191
 * @date:2022/4/16 18:00
 **/

@RestController
public class QueryController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/hello")
    public String hello(){
        List<QueryConfiguration> query = testMapper.getQuery();
        return query.get(0).getField_comment();
    }
}
