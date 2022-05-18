package com.example.cloudx_small_tools.service.impl;

import com.example.cloudx_small_tools.mapper.QueryConfigurationMapper;
import com.example.cloudx_small_tools.mapper.QueryNumMapper;
import com.example.cloudx_small_tools.mapper.QueryRelationMapper;
import com.example.cloudx_small_tools.po.QueryConfiguration;
import com.example.cloudx_small_tools.po.QueryNum;
import com.example.cloudx_small_tools.po.QueryRelation;
import com.example.cloudx_small_tools.service.QuerySqlService;
import com.example.cloudx_small_tools.util.CommonUtil;
import com.example.cloudx_small_tools.vo.QueryVO;
import com.example.cloudx_small_tools.vo.WhereVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: SQL 生成服务实现类
 * @author: 10191
 * @date:2022/4/19 17:09
 **/
@Service("querySqlService")
public class QuerySqlServiceImpl implements QuerySqlService {

    private static final Logger logger = LoggerFactory.getLogger(QuerySqlServiceImpl.class);

    @Autowired
    private QueryNumMapper queryNumMapper;
    @Autowired
    private QueryConfigurationMapper queryConfigurationMapper;
    @Autowired
    private QueryRelationMapper queryRelationMapper;

    @Override
    public String getQuerySql(List<QueryVO> queryVOList) {
        if (CollectionUtils.isEmpty(queryVOList)){
            throw new IllegalArgumentException("查询SQL生成, 入参为空");
        }

        StringBuffer sql = new StringBuffer();

        /**
         * 1.别名处理
         */
        aliasHandle(queryVOList);

        List<QueryConfiguration> queryFieldList = new ArrayList<>();
        List<WhereVO> queryWhereList = new ArrayList<>();
        queryVOList.forEach(e ->{
            if (!CollectionUtils.isEmpty(e.getQueryFields())){
                queryFieldList.addAll(e.getQueryFields());
            }

            if (!CollectionUtils.isEmpty(e.getWhereList())){
                queryWhereList.addAll(e.getWhereList());
            }
        });

        /**
         * 2.查询字段处理
         */
        sql.append("SELECT ");
        sql.append("\n");
        for (QueryConfiguration queryField : queryFieldList) {
            if (CommonUtil.isZero(queryField.getNum_id())) {
                sql.append(queryField.getQuery_field());
                sql.append(" AS '");
                sql.append(queryField.getField_comment());
                sql.append("',");
                sql.append("\n");
            } else {
                List<QueryNum> numList = queryNumMapper.getQueryNumByNumId(queryField.getNum_id());
                CommonUtil.isNull(numList, String.format("查询SQL生成，获取枚举表数据为空，枚举关联ID：%s，请配置枚举", queryField.getNum_id()));
                sql.append("CASE ");
                sql.append(queryField.getQuery_field());
                sql.append("\n");
                for (QueryNum queryNum : numList) {
                    sql.append("WHEN '");
                    sql.append(queryNum.getNum_value());
                    sql.append("' THEN '");
                    sql.append(queryNum.getNum_comment());
                    sql.append("'");
                    sql.append("\n");
                }
                sql.append("END AS '");
                sql.append(queryField.getField_comment());
                sql.append("',");
                sql.append("\n");
            }
        }

        /**
         * 3.表关联处理
         */
        sql.deleteCharAt(sql.length() - 2);

        for (QueryVO queryVO : queryVOList) {
            switch (queryVO.getTableType()){
                case "BASE":
                    sql.append("FROM ");
                    sql.append(queryVO.getQueryTable());
                    sql.append("\n");
                    break;
                case "RELATION":
                    sql.append("INNER JOIN ");
                    sql.append(queryVO.getQueryRelation().getAssociation_table());
                    sql.append(" ON ");
                    sql.append(queryVO.getQueryRelation().getAssociation_field());
                    sql.append(" = ");
                    sql.append(queryVO.getQueryRelation().getBase_field());
                    sql.append("\n");
                    break;
                default:
                    break;
            }
        }

        /**
         * 4.查询条件处理
         */
        sql.append("WHERE 1=1");
        sql.append("\n");


        if (!CollectionUtils.isEmpty(queryWhereList)) {
            for (WhereVO whereVO : queryWhereList) {
                switch (whereVO.getOperator()) {
                    case "IN":
                        sql.append("AND " + whereVO.getField() + " IN('" + StringUtils.join(whereVO.getValue(), "', '") + "')");
                        sql.append("\n");
                        break;
                    case "IS NULL":
                    case "IS NOT NULL":
                        sql.append("AND " + whereVO.getField() + " " + whereVO.getOperator());
                        sql.append("\n");
                        break;
                    case "LIKE":
                    case "NOT LIKE":
                        sql.append("AND " + whereVO.getField() + " " + whereVO.getOperator() + " '%" + whereVO.getValue() + "%'");
                        sql.append("\n");
                        break;
                    default:
                        sql.append("AND " + whereVO.getField() + " " + whereVO.getOperator() + " '" + whereVO.getValue() + "'");
                        sql.append("\n");
                        break;
                }
            }
        }

        sql.deleteCharAt(sql.length() - 1);
        logger.info("查询SQL：\n" + sql.toString());
        return sql.toString();

    }

    private static void aliasHandle(List<QueryVO> queryVOList){
        for (QueryVO vo : queryVOList) {
            String queryAlias = vo.getTableType().equals("BASE") ? vo.getBaseAlias() : vo.getRelationAlias();
            vo.setQueryTable(vo.getQueryTable() + " " + queryAlias);
            if (!ObjectUtils.isEmpty(vo.getQueryRelation())){
                vo.getQueryRelation().setBase_table(vo.getQueryRelation().getBase_table() + " " + vo.getBaseAlias());
                vo.getQueryRelation().setBase_field(vo.getBaseAlias() + "." + vo.getQueryRelation().getBase_field());
                vo.getQueryRelation().setAssociation_table(vo.getQueryRelation().getAssociation_table() + " " + vo.getRelationAlias());
                vo.getQueryRelation().setAssociation_field(vo.getRelationAlias() + "." + vo.getQueryRelation().getAssociation_field());
            }
            if (!CollectionUtils.isEmpty(vo.getQueryFields())){
                for (QueryConfiguration field : vo.getQueryFields()) {
                    field.setQuery_table(field.getQuery_table() + " " + queryAlias);
                    field.setQuery_field(queryAlias + "." + field.getQuery_field());
                }
            }
            if (!CollectionUtils.isEmpty(vo.getWhereList())){
                for (WhereVO whereVO : vo.getWhereList()) {
                    whereVO.setField(queryAlias + "." + whereVO.getField());
                }
            }
        }
    }

    @Override
    public List<QueryConfiguration> getQueryConfig(String table) {
        Assert.isTrue(StringUtils.isNotBlank(table), "查询表字段配置信息，入参【table】为空");
        return queryConfigurationMapper.getQueryConfigByTable(table);
    }

    @Override
    public List<QueryRelation> getQueryRelation(String table) {
        Assert.isTrue(StringUtils.isNotBlank(table), "查询表关联信息，入参【table】为空");
        return queryRelationMapper.getQueryRelationByBaseTable(table);
    }
}
