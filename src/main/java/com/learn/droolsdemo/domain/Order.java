package com.learn.droolsdemo.domain;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/30 上午11:23
 * @Description:
 */
public class Order {

    private int amount;

    private User user;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
