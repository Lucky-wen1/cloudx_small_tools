package com.example.cloudx_small_tools.service.impl;

import com.alibaba.fastjson.JSON;
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
    private QueryConfigurationMapper queryConfigurationMapper;
    @Autowired
    private QueryNumMapper queryNumMapper;
    @Autowired
    private QueryRelationMapper queryRelationMapper;

    @Override
    public String getQuerySql(QueryVO queryVO) {
        CommonUtil.isNull(queryVO, "查询SQL生成, 入参为空");
        CommonUtil.isNull(queryVO.getConfigIds(), "查询SQL生成，入参【配置表IDS】为空");
        CommonUtil.isNull(queryVO.getBase_table(), "查询SQL生成，入参【主表BASETABLE】为空");
        List<QueryConfiguration> configList = queryConfigurationMapper.getQueryConfigByIds(queryVO.getConfigIds());
        CommonUtil.isNull(configList, String.format("查询SQL生成，获取字段配置数据为空，IDS：%s", JSON.toJSONString(queryVO.getConfigIds())));
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT ");
        sql.append("\n");
        for (QueryConfiguration field : configList) {
            if (CommonUtil.isZero(field.getNum_id())){
                sql.append(field.getQuery_field());
                sql.append(" AS '");
                sql.append(field.getField_comment());
                sql.append("',");
                sql.append("\n");
            } else {
                List<QueryNum> numList = queryNumMapper.getQueryNumByNumId(field.getNum_id());
                CommonUtil.isNull(numList, String.format("查询SQL生成，获取枚举表数据为空，枚举关联ID：%s，请配置枚举", field.getNum_id()));
                sql.append("CASE ");
                sql.append(field.getQuery_field());
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
                sql.append(field.getField_comment());
                sql.append("',");
                sql.append("\n");
            }

            if (!CommonUtil.isZero(field.getRelation_id())){
                queryVO.getRelationIds().add(field.getRelation_id());
            }
        }
        sql.deleteCharAt(sql.length() - 2);
        sql.append("FROM ");
        sql.append(queryVO.getBase_table());
        sql.append("\n");
        if(!CommonUtil.isNull(queryVO.getRelationIds())){
            List<QueryRelation> relationList = queryRelationMapper.getQueryRelationByIds(queryVO.getRelationIds());
            CommonUtil.isNull(relationList, String.format("查询SQL生成，获取表关联配置数据为空，IDS：%s", JSON.toJSONString(queryVO.getRelationIds())));
            for (QueryRelation relation : relationList) {
                sql.append("INNER JOIN ");
                sql.append(relation.getAssociation_table());
                sql.append(" ON ");
                sql.append(relation.getBase_field());
                sql.append(" = ");
                sql.append(relation.getAssociation_field());
                sql.append("\n");
            }
        }
        sql.append("WHERE 1=1");
        sql.append("\n");
        whereSqlhandle(sql, queryVO.getWhere());
        sql.deleteCharAt(sql.length() - 1);
        logger.info("查询SQL：\n" + sql.toString());
        return sql.toString();
    }

    public static void whereSqlhandle(StringBuffer sql, List<WhereVO> whereList){
       if (CommonUtil.isNull(whereList)){
           return;
       }
        for (WhereVO whereVO : whereList) {
            switch (whereVO.getOperator()){
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
}
