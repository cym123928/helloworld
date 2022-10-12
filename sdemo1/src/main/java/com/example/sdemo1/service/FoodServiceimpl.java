package com.example.sdemo1.service;

import com.example.sdemo1.dao.FoodDao;
import com.example.sdemo1.dao.UserDao;
import com.example.sdemo1.pojo.Food;
import com.example.sdemo1.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FoodServiceimpl implements FoodService{
    @Resource
    FoodDao foodDao;
    @Override
    public int addF(Food food) {
        return foodDao.addF(food);
    }

    @Override
    public int deleteF(Food food) {
        return foodDao.deleteF(food);
    }

    @Override
    public List<Food> findByRName(int name) {
        return foodDao.findByRName(name);
    }

    @Override
    public Food find(String name) {
        return foodDao.find(name);
    }

    @Override
    public int updateF(Food food) {
        return foodDao.updateF(food);
    }

    @Override
    public List<Food> search(String name,int number) {
         return foodDao.search(name,number);
    }
}
