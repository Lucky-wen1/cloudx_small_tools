package com.example.cloudx_small_tools.po;

import java.io.Serializable;

/**
 * @Description: 值班人员配置
 * @author: 10191
 * @date:2022/3/12 14:10
 **/
public class DutyUserConfigure implements Serializable {

    private static final long serialVersionUID = -7022140771248321332L;

    /** @Description ID */
    private Long id;

    /** @Description 值班用户id */
    private Long user_id;

    /** @Description 值班分组 */
    private String duty_group;

    /** @Description 值班状态 1：参与值班， 0：不参与值班*/
    private boolean duty_status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getDuty_group() {
        return duty_group;
    }

    public void setDuty_group(String duty_group) {
        this.duty_group = duty_group;
    }

    public boolean isDuty_status() {
        return duty_status;
    }

    public void setDuty_status(boolean duty_status) {
        this.duty_status = duty_status;
    }
}
