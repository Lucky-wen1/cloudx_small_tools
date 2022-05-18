package com.example.cloudx_small_tools.vo;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * api结果集封装对象
 *
 * @author lsf
 *
 */
public class WebResult implements Serializable {

    private static final long serialVersionUID = -3083535044907251604L;

    /**
     * 结果（true/false）
     */
    private Boolean result;
    /**
     * 提示码
     */
    private  String code ;

    /**
     * 提示
     */
    private String msg;

    /**
     * 内容对象
     */
    private Object content;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public WebResult() {

    }

    public WebResult(Boolean result, String msg, Object content) {
        this.result = result;
        this.msg = msg;
        this.content = content;
    }

    public WebResult(Boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public WebResult(Boolean result, Object content) {
        this.result = result;
        this.content = content;
    }

    /**
     * 将对象转换为JSON字符串
     */
    public String toJSONString() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public WebResult(Boolean result, String msg , String code,String type) {
        this.result = result;
        this.code = code;
        this.msg = msg;
    }

}
