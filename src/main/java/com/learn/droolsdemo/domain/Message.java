package com.learn.droolsdemo.domain;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/30 上午10:44
 * @Description:
 */
public class Message {
    public static final Integer HELLO = 0;
    public static final Integer GOODBYE = 1;

    private String message;

    private Integer status;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
