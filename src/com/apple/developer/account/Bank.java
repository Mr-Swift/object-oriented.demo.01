package com.apple.developer.account;

import java.util.Scanner;

public class Bank {

    Account[] accounts = new Account[10000];

    public Scanner input = new Scanner(System.in);

    public void menu() {
        do {
            System.out.println("\n\n");
            System.out.println("*********************");
            System.out.println("欢迎使用银行账户管理系统！");
            System.out.println("*********************");
            System.out.println("1、添加账户");
            System.out.println("2、取钱");
            System.out.println("3、存钱");
            System.out.println("4、转账");
            System.out.println("5、挂失账户");
            System.out.println("0、退出系统");
            System.out.println("*********************");
            System.out.print("请选择：");
            int choice1 = input.nextInt();

            switch (choice1) {
                case 0:
                    System.exit(0);
                case 1:
                    addAccount();
                    break;
                case 2:
                    drawMoney();
                    break;
                case 3:
                    saveMoney();
                    break;
                case 4:
                    transferAccounts();
                    break;
                case 5:
                    reportLoss();
                    break;
                default:
                    break;
            }
        } while (true);
    }

    private void reportLoss() {
        System.out.print("请输入您的银行卡号：");
        String name = input.next();
        System.out.print("请输入您的密码：");
        String password = input.next();
        if (findByName(name) != null) {
            int index = findIndexByName(name);
            if (accounts[index].getPassword().equals(password)) {
                System.out.println("账号登陆成功！");
                System.out.print("挂失确认（是/否)");
                String in = input.next();
                if (in.equals("是") == true) {
                    accounts[index].setStatus(AccountStatus.STATUS_2);
                }
            } else {
                System.out.println("密码错误！");
            }
        } else {
            System.out.println("您输入的的账号不存在！");
        }
    }

    private void transferAccounts() {
        System.out.print("请输入您的银行卡号：");
        String name = input.next();
        System.out.print("请输入您的密码：");
        String password = input.next();
        if (findByName(name) != null) {
            int index = findIndexByName(name);
            if (accounts[index].getPassword().equals(password)) {
                if (accounts[index].getStatus().equals(AccountStatus.STATUS_2)) {
                    System.out.println("该账户已被挂失，请到银行柜台办理解除挂失！");
                    return;
                }
                System.out.println("账号登陆成功！");
                System.out.print("请输入您要转入的账户：");
                String name1 = input.next();
                if (findByName(name1) != null) {
                    int index1 = findIndexByName(name1);
                    System.out.println("您的账户余额为：" + accounts[index].getBalance());
                    System.out.print("请输入转账金额：");
                    double num = input.nextInt();
                    if (accounts[index].getBalance() < num) {
                        System.out.println("您的余额不足！");
                    } else {
                        accounts[index].drawMoney(num);
                        accounts[index1].saveMoney(num);
                        System.out.println("转账成功，您已成功给" + name1 + "转账" + num + "元,您的余额为" + accounts[index].getBalance() + "元");
                    }
                } else {
                    System.out.println("您要转入的账户不存在！");
                }
            } else {
                System.out.println("密码错误！");
            }
        } else {
            System.out.println("您输入的的账号不存在！");
        }
    }

    private void saveMoney() {
        System.out.print("请输入您的银行卡号：");
        String name = input.next();
        System.out.print("请输入您的密码：");
        String password = input.next();
        if (findByName(name) != null) {
            int index = findIndexByName(name);
            if (accounts[index].getPassword().equals(password)) {
                if (accounts[index].getStatus().equals(AccountStatus.STATUS_2)) {
                    System.out.println("该账户已被挂失，请到银行柜台办理解除挂失！");
                    return;
                }
                System.out.println("账号登陆成功！");
                System.out.print("请输入存款金额：");
                double num = input.nextInt();
                accounts[index].saveMoney(num);
                System.out.println("您已经成功存款" + num + ",账户余额为" + accounts[index].getBalance());
            } else {
                System.out.println("密码错误！");
            }
        } else {
            System.out.println("您输入的的账号不存在！");
        }
    }

    private void drawMoney() {
        System.out.print("请输入您的银行卡号：");
        String name = input.next();
        System.out.print("请输入您的密码：");
        String password = input.next();
        if (findByName(name) != null) {
            int index = findIndexByName(name);
            if (accounts[index].getPassword().equals(password)) {
                if (accounts[index].getStatus().equals(AccountStatus.STATUS_2)) {
                    System.out.println("该账户已被挂失，请到银行柜台办理解除挂失！");
                    return;
                }
                System.out.println("账号登陆成功！");
                System.out.print("请输入取款金额：");
                double num = input.nextInt();
                accounts[index].drawMoney(num);
                System.out.println("您已经成功取款" + num + ",账户余额为" + accounts[index].getBalance());
            } else {
                System.out.println("密码错误！");
            }
        } else {
            System.out.println("您输入的的账号不存在！");
        }
    }


    private void addAccount() {
        System.out.println("--->添加账户");
        System.out.print("请输入您要添加账户的账户名：");
        String name = input.next();
        Account findName = findByName(name);
        if (findName != null) {
            System.out.println("该账户已存在，不可重复添加！");
        } else {
            System.out.print("请设置你的密码：");
            String password = input.next();
            System.out.print("请再次输入你的密码:");
            String password1 = input.next();
            if (password1.equals(password) == true) {
                Account accountNew = new Account(name, 0, password);
                for (int i = 0; i < accounts.length; i++) {
                    if (accounts[i] == null) {
                        accounts[i] = accountNew;
                        System.out.println("账户添加成功！");
                        System.out.println(accounts[i].showBalance());
                        break;
                    }
                }
            }
        }
    }

    private int findIndexByName(String name) {
        int index = -1;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getName().equals(name) == true) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    private Account findByName(String name) {
        Account account = null;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                if (name.equals(accounts[i].getName()) == true) {
                    account = accounts[i];
                    break;
                }
            }
        }
        return account;
    }
}
