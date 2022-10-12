package com.example.sdemo1.pojo;

public class Answer implements Comparable<Answer>{
    public int number,type,count,id;
    public String response,name,phone,time;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

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

    public String getTime() {
        return time;
    }
    public int type(String title){
        int t=0;
        if(title.equals("主任医师"))
            t=4;
        else if(title.equals("副主任医师"))
            t=3;
        else if(title.equals("主治医师"))
            t=2;
        else if(title.equals("住院医师"))
            t=1;
        else
            t=0;

        return t;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int good()
    {
        return type*100+count/100;
    }
    @Override
    public int compareTo(Answer o) {
        return o.good()-this.good();
    }
}
