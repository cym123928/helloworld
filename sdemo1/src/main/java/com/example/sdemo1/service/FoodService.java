package com.example.sdemo1.service;

import com.example.sdemo1.pojo.Food;

import java.util.List;

public interface FoodService {
    int addF(Food food);
    int deleteF(Food food);
    List<Food> findByRName(int name);
    Food find(String name);
    int updateF(Food food);
    List <Food> search(String name,int number);
}
