package com.example.cloudx_small_tools.vo;

import java.io.Serializable;

/**
 * @Description: 值班规则表VO
 * @author: 10191
 * @date:2022/3/12 14:54
 **/
public class DutyVO implements Serializable {

    private static final long serialVersionUID = 7909971123344261411L;

    /** @Description 格式化日期 */
    private String date;

    /** @Description 值班分组 */
    private String duty_group;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuty_group() {
        return duty_group;
    }

    public void setDuty_group(String duty_group) {
        this.duty_group = duty_group;
    }
}
