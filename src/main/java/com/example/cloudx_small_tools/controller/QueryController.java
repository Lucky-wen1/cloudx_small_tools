package com.example.cloudx_small_tools.controller;
import com.example.cloudx_small_tools.mapper.QueryConfigurationMapper;
import com.example.cloudx_small_tools.mapper.QueryRelationMapper;
import com.example.cloudx_small_tools.service.QuerySqlService;
import com.example.cloudx_small_tools.vo.QueryVO;
import com.example.cloudx_small_tools.vo.WebResult;
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
 * @Description: SQL生成
 * @author: 10191
 * @date:2022/4/16 18:00
 **/

@RestController
public class QueryController {

    private static final Logger logger = LoggerFactory.getLogger(QueryController.class);

    @Autowired
    private QueryConfigurationMapper queryConfigurationMapper;

    @Autowired
    private QueryRelationMapper queryRelationMapper;

    @Autowired
    private QuerySqlService querySqlService;

    @RequestMapping("listField")
    public WebResult listField(String table){
        WebResult webResult = new WebResult();
        try {
            webResult.setCode("SUCESS");
            querySqlService.getQueryConfig(table);
        }catch (Exception e){

        }
        return webResult;
    }

    @RequestMapping("/getQuerySql")
    public String getQuerySql(){
        List<QueryVO> list = new ArrayList<>();
        QueryVO vo1 = new QueryVO();
        vo1.setQueryTable("scm_sys_trade");
        vo1.setBaseAlias("sst");
        vo1.setRelationAlias(null);
        vo1.setQueryFields(queryConfigurationMapper.getQueryConfigByIds(Arrays.asList(1L,2L,3L,4L,5L)));
        vo1.setQueryRelation(null);
        List<WhereVO> whereList = new ArrayList<>();
        WhereVO whereVO1 = new WhereVO();
        WhereVO whereVO2 = new WhereVO();
        whereVO1.setField("shop_id");
        whereVO1.setValue("111");
        whereVO1.setOperator("=");
        whereVO2.setField("merge_trade_id");
        whereVO2.setValue("222");
        whereVO2.setOperator("LIKE");
        whereList.add(whereVO1);
        whereList.add(whereVO2);
        vo1.setWhereList(whereList);
        vo1.setTableType("BASE");
        QueryVO vo2 = new QueryVO();
        vo2.setQueryTable("scm_merge_trade");
        vo2.setBaseAlias("sst");
        vo2.setRelationAlias("smt");
        vo2.setQueryFields(null);
        vo2.setQueryRelation(queryRelationMapper.getQueryRelationById(1L));
        vo2.setWhereList(null);
        vo2.setTableType("RELATION");
        QueryVO vo3 = new QueryVO();
        vo3.setQueryTable("cloud_shop_v2");
        vo3.setBaseAlias("sst");
        vo3.setRelationAlias("csv");
        vo3.setQueryFields(queryConfigurationMapper.getQueryConfigByIds(Arrays.asList(9L,10L)));
        vo3.setQueryRelation(queryRelationMapper.getQueryRelationById(3L));
        List<WhereVO> whereList1 = new ArrayList<>();
        WhereVO whereVO3 = new WhereVO();
        whereVO3.setField("shop_id");
        whereVO3.setValue("333");
        whereVO3.setOperator("=");
        whereList1.add(whereVO3);
        vo3.setWhereList(whereList1);
        vo3.setTableType("RELATION");
        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        return querySqlService.getQuerySql(list);
    }
}
