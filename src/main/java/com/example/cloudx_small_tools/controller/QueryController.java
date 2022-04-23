package com.example.cloudx_small_tools.controller;
import com.example.cloudx_small_tools.service.QuerySqlService;
import com.example.cloudx_small_tools.vo.QueryVO;
import com.example.cloudx_small_tools.vo.WhereVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @author: 10191
 * @date:2022/4/16 18:00
 **/

@RestController
public class QueryController {

    private static final Logger logger = LoggerFactory.getLogger(QueryController.class);

    @Autowired
    private QuerySqlService querySqlService;

    @RequestMapping("/getQuerySql")
    public String getQuerySql(){
        List<Long> configIds = new ArrayList<>();
        configIds.add(1L);
        configIds.add(2L);
        configIds.add(3L);
        configIds.add(4L);
        configIds.add(5L);
        configIds.add(6L);
        List<Long> relationIds = new ArrayList<>();
        relationIds.add(2L);
        QueryVO queryVO = new QueryVO();
        queryVO.setConfigIds(configIds);
        queryVO.setRelationIds(relationIds);
        String baseTable = "scm_sys_trade sst";
        queryVO.setBase_table(baseTable);
        List<WhereVO> whereVOS = new ArrayList<>();
        WhereVO whereVO = new WhereVO();
        whereVO.setField("sst.merge_trade_id");
        whereVO.setOperator("=");
        whereVO.setValue("12345678");
        whereVOS.add(whereVO);
        WhereVO whereVO1 = new WhereVO();
        whereVO1.setField("sst.user_id");
        whereVO1.setOperator("IN");
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        whereVO1.setValue(integers);
        whereVOS.add(whereVO1);
        queryVO.setWhere(whereVOS);
        WhereVO whereVO2 = new WhereVO();
        whereVO2.setField("sst.merge_trade_id");
        whereVO2.setOperator("IS NULL");
        whereVO2.setValue("12345678");
        whereVOS.add(whereVO2);
        WhereVO whereVO3 = new WhereVO();
        whereVO3.setField("sst.merge_trade_id");
        whereVO3.setOperator("LIKE");
        whereVO3.setValue("12345678");
        whereVOS.add(whereVO3);
        String sql = querySqlService.getQuerySql(queryVO);
        return sql;
    }
}
