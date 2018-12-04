package com.learn.droolsdemo.domain;

/**
 * @Author: chengyong.wang
 * @Date: 2018/9/30 下午2:48
 * @Description:
 */
public class Account {
    private long   accountNo;
    private double balance = 0;

    public Account() {
    }

    public Account(long accountNo) {
        this.accountNo = accountNo;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account[" + "accountNo=" + accountNo + ",balance=" + balance + "]";
    }
}
