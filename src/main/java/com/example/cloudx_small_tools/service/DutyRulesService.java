package com.example.cloudx_small_tools.service;

import java.time.DateTimeException;
import java.util.List;

/**
 * @Description: 值班规则Service
 * @author: 10191
 * @date:2022/3/12 10:29
 **/
public interface DutyRulesService {

    /**
     * 值班规则表生成
     */
    List<String> dutyRulesTableCreate(int year) throws DateTimeException;

}
