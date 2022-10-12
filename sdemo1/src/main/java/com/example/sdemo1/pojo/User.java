package com.example.sdemo1.pojo;



import java.util.ArrayList;

public class User {   //用户1，商家2，外卖员3
    public String name,phone,password;
    public String type;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType(String type){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }


}
