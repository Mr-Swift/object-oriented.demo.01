package com.apple.developer.employee;

public class Employee {
    private String id;
    private String name;
    private double salary;
    private String department;

    public Employee(String id,String name,double salary,String department){
        this.id=id;
        this.name=name;
        this.salary=salary;
        this.department=department;
    }

    public void print(){
        System.out.println("员工号："+id+"\t姓名："+name+"\t薪水："+salary+"\t部门："+department);
    }
}
