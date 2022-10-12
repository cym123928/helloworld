package com.example.sdemo1.pojo;

import java.util.List;

public class Page <T>{
    int total;
    int current;
    int size;
   // Question question;
    Food food;
    Restaurant restaurant;

    public void setFood(Food food) {
        this.food = food;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    List<T> item;
public Page()
{}
    public Page(int total,int size,int current)
    {
        this.total=total;
        this.size=size;
        this.current=current;
    }

  /*  public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }*/

    public Food getFood(){
        return food;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }

}
