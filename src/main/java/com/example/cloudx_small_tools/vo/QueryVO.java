package com.example.cloudx_small_tools.vo;

import com.example.cloudx_small_tools.po.QueryConfiguration;
import com.example.cloudx_small_tools.po.QueryRelation;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: SQL生成 查询VO
 * @author: 10191
 * @date:2022/4/20 11:29
 **/
public class QueryVO implements Serializable {

    private static final long serialVersionUID = -4271776627306209682L;

    private String queryTable;
    private String baseAlias;
    private String relationAlias;
    private List<QueryConfiguration> queryFields;
    private QueryRelation queryRelation;
    private List<WhereVO> WhereList;
    private String tableType;

    public String getQueryTable() {
        return queryTable;
    }

    public void setQueryTable(String queryTable) {
        this.queryTable = queryTable;
    }

    public String getBaseAlias() {
        return baseAlias;
    }

    public void setBaseAlias(String baseAlias) {
        this.baseAlias = baseAlias;
    }

    public String getRelationAlias() {
        return relationAlias;
    }

    public void setRelationAlias(String relationAlias) {
        this.relationAlias = relationAlias;
    }

    public List<QueryConfiguration> getQueryFields() {
        return queryFields;
    }

    public void setQueryFields(List<QueryConfiguration> queryFields) {
        this.queryFields = queryFields;
    }

    public QueryRelation getQueryRelation() {
        return queryRelation;
    }

    public void setQueryRelation(QueryRelation queryRelation) {
        this.queryRelation = queryRelation;
    }

    public List<WhereVO> getWhereList() {
        return WhereList;
    }

    public void setWhereList(List<WhereVO> whereList) {
        WhereList = whereList;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
}
