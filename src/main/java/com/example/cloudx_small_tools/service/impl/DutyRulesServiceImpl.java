package com.example.cloudx_small_tools.service.impl;

import com.example.cloudx_small_tools.service.DutyRulesService;
import com.example.cloudx_small_tools.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.*;

import static com.example.cloudx_small_tools.util.DateUtils.*;

/**
 * @Description: 值班规则表
 * @author: 10191
 * @date:2022/3/12 10:30
 **/
@Service("dutyRulesService")
public class DutyRulesServiceImpl implements DutyRulesService {

    private static final Logger logger = LoggerFactory.getLogger(DutyRulesServiceImpl.class);

    @Override
    public List<String> dutyRulesTableCreate(int year) throws DateTimeException {
        if (year < 1970 || year > 9999){
            throw new DateTimeException("当前输入年份不合法");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 0);
        Date lastYearLastDate = calendar.getTime(); // 上一年最后一天
        String lastYearDutyGroup = "A";             // 上一年最后一天值班分组
        // 根据上一年最后一天的值班分组获取对分组进行排序
        List<String> groupList = getGroup(lastYearLastDate,lastYearDutyGroup);

        // 获取当前年所有值班日期
        List<Date> dutyRulesDates = getDutyRulesDates(year);
        int groupCount = 3; // 值班分组数
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM月dd日");
        List<String> dutyList = new ArrayList<>();
        for (int i = 0; i < dutyRulesDates.size(); i++) {
            int flag = (i + groupCount * 2) % (groupCount * 2);
            if (flag == 0 || flag == 1){
                dutyList.add(dateFormat.format(dutyRulesDates.get(i)) + "(" + groupList.get(0) + "班上班)");
            }
            if (flag == 2 || flag == 3){
                dutyList.add(dateFormat.format(dutyRulesDates.get(i)) + "(" + groupList.get(1) + "班上班)");
            }
            if (flag == 4 || flag == 5) {
                dutyList.add(dateFormat.format(dutyRulesDates.get(i)) + "(" + groupList.get(2) + "班上班)");
            }
        }
        return dutyList;
    }

    public List<Date> getDutyRulesDates(int year){
        List<Date> dutyRulesDates = new ArrayList<>();
        // 获取一年所有日期
        List<Date> currentYaerAllDates = DateUtils.getAllDatesOfYear(year);
        Date firstDay = currentYaerAllDates.get(0);                             // 当前年头一天
        Date lastDay = currentYaerAllDates.get(currentYaerAllDates.size() - 1); // 当前年最后一天
        String firstDayWeek = DateUtils.getWeekOfDate(firstDay);                // 当前年头一天星期数
        String lastDayWeek = DateUtils.getWeekOfDate(lastDay);                  // 当前年最后一天星期数
        // 获取上一年末值班日期
        List<Date> lastYearDate = getLastYearDate(firstDay, firstDayWeek);
        // 获取下一年初值班日期
        List<Date> nextYearDate = getNextYearDate(lastDay, lastDayWeek);
        // 周末值班
        List<String> dutyWeekList = Arrays.asList(FRIDAY, SATURDAY, SUNDAY, MONDAY);
        if (!CollectionUtils.isEmpty(lastYearDate)){
            dutyRulesDates.addAll(lastYearDate);
        }
        for (Date date : currentYaerAllDates) {
            if (dutyWeekList.contains(getWeekOfDate(date))){
                dutyRulesDates.add(date);
            }
        }
        if (!CollectionUtils.isEmpty(nextYearDate)){
            dutyRulesDates.addAll(nextYearDate);
        }
        return dutyRulesDates;
    }

    /**
     * 获取上一年末值班日期
     * @param firstDay
     * @param firstDayWeek
     * @return
     */
    public  List<Date> getLastYearDate(Date firstDay, String firstDayWeek){
        List<Date> lastYearDate = new ArrayList<>();    // 上一年日期
        switch (firstDayWeek){
            case SATURDAY:
                lastYearDate.add(DateUtils.addDays(firstDay , -1));
                break;
            case SUNDAY:
                lastYearDate.add(DateUtils.addDays(firstDay, -2));
                lastYearDate.add(DateUtils.addDays(firstDay, -1));
                break;
            case MONDAY:
                lastYearDate.add(DateUtils.addDays(firstDay, -3));
                lastYearDate.add(DateUtils.addDays(firstDay, -2));
                lastYearDate.add(DateUtils.addDays(firstDay, -1));
                break;
            default:
                break;
        }
        return lastYearDate;
    }

    /**
     * 获取下一年初值班日期
     * @param lastDay
     * @param lastDayWeek
     * @return
     */
    public  List<Date> getNextYearDate(Date lastDay, String lastDayWeek){
        List<Date> nextYearDate = new ArrayList<>();    // 下一年日期
        switch (lastDayWeek){
            case FRIDAY:
                nextYearDate.add(DateUtils.addDays(lastDay, 1));
                nextYearDate.add(DateUtils.addDays(lastDay, 2));
                nextYearDate.add(DateUtils.addDays(lastDay, 3));
                break;
            case SATURDAY:
                nextYearDate.add(DateUtils.addDays(lastDay, 1));
                nextYearDate.add(DateUtils.addDays(lastDay, 2));
                break;
            case SUNDAY:
                nextYearDate.add(DateUtils.addDays(lastDay, 1));
                break;
            default:
                break;
        }
        return nextYearDate;
    }

    /**
     * 根据上一年最后一天的值班分组获取分组排序
     * @param lastYearDutyGroup
     * @return
     */
    public List<String> getGroup(Date lastYearLastDate, String lastYearDutyGroup){
        String week = getWeekOfDate(lastYearLastDate);
        if (MONDAY.equals(week)){
            switch (lastYearDutyGroup){
                case "B":
                    return Arrays.asList("C", "A", "B");
                case "C":
                    return Arrays.asList("A", "B", "C");
                default:
                    return Arrays.asList("B", "C", "A");
            }
        } else {
            switch (lastYearDutyGroup){
                case "C":
                    return Arrays.asList("C", "A", "B");
                case "A":
                    return Arrays.asList("A", "B", "C");
                default:
                    return Arrays.asList("B", "C", "A");
            }
        }
    }

    public static void main(String[] args) {
        DutyRulesServiceImpl service = new DutyRulesServiceImpl();
        service.dutyRulesTableCreate(2022);
    }
}
