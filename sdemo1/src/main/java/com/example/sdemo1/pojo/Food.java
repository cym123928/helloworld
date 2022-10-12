package com.example.sdemo1.pojo;

public class Food {
    public String name;
    public double price;  //不同餐厅的食物的价格
    public int number;  //餐厅号






    /* @Override
        public int compareTo(Food o) {
            if(this.number==o.number)
                return o.name-this.name;
            else
                return o.type()-this.type();
        }*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
