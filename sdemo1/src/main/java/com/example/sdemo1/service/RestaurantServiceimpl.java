package com.example.sdemo1.service;

import com.example.sdemo1.dao.RestaurantDao;
import com.example.sdemo1.pojo.Food;
import com.example.sdemo1.pojo.Restaurant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class RestaurantServiceimpl implements RestaurantService {
    @Resource
    RestaurantDao restaurantDao;
    @Override
    public int addR(Restaurant restaurant){
        return restaurantDao.addR(restaurant);
    }
    @Override
    public int deleteR(Restaurant restaurant){
        return restaurantDao.deleteR(restaurant);
    }
    @Override
    public int deleteF(Restaurant restaurant){
        return restaurantDao.deleteF(restaurant);
    }


    @Override
    public List<Restaurant> collect(String phone) {
        return restaurantDao.collect(phone);
    }


    @Override
    public Restaurant find(int number) {
        return restaurantDao.find(number);
    }

    @Override
    public List<Restaurant> search(String word) {
        return restaurantDao.search(word);
    }

    @Override
    public List<Restaurant> all() {
        return restaurantDao.all();
    }
}
