package com.example.sdemo1.pojo;

public class SR {
    public int number;  //餐厅号
    public String name;  //餐厅名
    public String boss; //商家电话号码
    public String city; //商家地址
   // public String name; //商家名
    //public double price;  //价格

    public String getRname() {
        return name;
    }

    public void setRname(String rname) {
        this.name = rname;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
