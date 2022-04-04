package com.example.cloudx_small_tools.po;

import java.io.Serializable;

/**
 * @Description: 用户
 * @author: 10191
 * @date:2022/3/12 9:59
 **/
public class User implements Serializable {

    private static final long serialVersionUID = -6164882601318771697L;

    /** @Description ID */
    private Long id;

    /** @Description 工号 */
    private String employee_number;

    /** @Description 真实姓名 */
    private String real_name;

    /** @Description 昵称 */
    private String nick_name;

    /** @Description 角色 */
    private String user_role;

    /** @Description 状态 1：生效 0：失效*/
    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(String employee_number) {
        this.employee_number = employee_number;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
