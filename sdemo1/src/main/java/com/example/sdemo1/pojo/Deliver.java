package com.example.sdemo1.pojo;

public class Deliver {
    public String phone;
    public int status;   //外卖员状态
    public int orderNumber;
   // public int value;   //0代表未配送订单  1代表正在配送  2已完成订单

    public String getPhone() {
        return phone;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

   /* public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }*/

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
