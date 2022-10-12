package com.example.sdemo1.service;

import com.example.sdemo1.pojo.Food;

import com.example.sdemo1.pojo.Restaurant;

import java.util.List;

public interface RestaurantService {
    int addR(Restaurant restaurant);
    int deleteR(Restaurant restaurant);
    int deleteF(Restaurant restaurant);
    List<Restaurant> collect(String phone);
    Restaurant find(int number);

    List<Restaurant> search(String word);

    List<Restaurant> all();
}
