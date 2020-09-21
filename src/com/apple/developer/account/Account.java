package com.apple.developer.account;

public class Account {
    private String accountName;
    private double balance;
    private String password;
    private String status;

    public Account(String accountName,double balance,String password){
        this.accountName=accountName;
        this.balance=balance;
        this.password=password;
        this.status=AccountStatus.STATUS_0;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getStatus(){
        return this.status;
    }

    public String getName(){
        return this.accountName;
    }

    public String getPassword(){
        return this.password;
    }

    public String showBalance(){
        return "账户名："+this.accountName+"\t余额："+this.balance+"\t账户状态："+this.status;
    }

    public void drawMoney(double num) {
        this.balance-=num;
    }

    public double getBalance() {
        return this.balance;
    }

    public void saveMoney(double num) {
        this.balance+=num;
    }
}
