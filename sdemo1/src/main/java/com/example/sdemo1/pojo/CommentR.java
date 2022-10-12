package com.example.sdemo1.pojo;

public class CommentR {
    public String phone, name,resCom,resCom1,orderTime,sendTime,address;
    //用户电话，姓名，评价，下单时间，送达时间，送餐地址  点餐详情
    public int orderNumber;
    public double price;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResCom() {
        return resCom;
    }

    public void setResCom(String resCom) {
        this.resCom = resCom;
    }

    public String getResCom1() {
        return resCom1;
    }

    public void setResCom1(String resCom1) {
        this.resCom1 = resCom1;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
