package com.learn.droolsdemo.domain;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/30 上午11:24
 * @Description:
 */
public class User {

    private int userId;

    private String userType;

    private int userScore;

    public User(int userId, String userType, int userScore) {
        this.userId = userId;
        this.userType = userType;
        this.userScore = userScore;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}
