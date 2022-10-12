package com.example.sdemo1.controller;

import com.example.sdemo1.pojo.*;
import com.example.sdemo1.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@ResponseBody
@RestController
public class RestaurantController {
    List<Restaurant> lists;
    @Resource
    private RestaurantService restaurantService;

    @RequestMapping("/restaurant/add")
    public Result<?> add(Restaurant restaurant) {
        System.out.println("aaa" + restaurant.name);
        restaurantService.addR(restaurant);
        System.out.println(restaurant.name);
        return Result.success("添加成功！");
    }

    @RequestMapping("/restaurant/delete")
    public Result<?> delete(Restaurant restaurant) {
        restaurantService.deleteR(restaurant);
        return Result.success("删除成功！");
    }

    @RequestMapping("/restaurant/deleteF")
    public Result<?> deleteF(Restaurant restaurant) {
        restaurantService.deleteF(restaurant);
        return Result.success("删除成功！");
    }


    @RequestMapping("/restaurant/collect")   //显示商家拥有的所有餐厅
    public Result<?> collect(String phone, int currentPage, int pageSize) {
        System.out.println(currentPage + " " + pageSize + "bbbcollect" + phone);
        Restaurant restaurant;
        int number = 0;
        List<Restaurant> restaurantList = new ArrayList<>();
        List<Restaurant> list = restaurantService.collect(phone);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            number = list.get(i).number;
            System.out.println(number);
            restaurant = restaurantService.find(number);
            System.out.println(restaurant.name);
            if (!restaurantList.contains(restaurant))
                restaurantList.add(restaurant);
        }
        Page<Restaurant> page = new Page<>(restaurantList.size(), pageSize, currentPage);
        List<Restaurant> list2 = new ArrayList<>();
        int n = (currentPage - 1) * pageSize;
        for (int i = 1; i <= pageSize && n < page.getTotal(); i++) {
            list2.add(restaurantList.get(n));
            System.out.println(restaurantList.get(n).name);
            n++;
        }
        page.setItem(list2);
        return Result.success(page);
    }

    //显示所有餐厅
    @RequestMapping("/restaurant/all")
    public Result<?> all(int currentPage, int pageSize){
      List<Restaurant> restaurantList = restaurantService.all();
        Page<Restaurant> page = new Page<>(restaurantList.size(), pageSize, currentPage);
        List<Restaurant> list2 = new ArrayList<>();
        int n = (currentPage - 1) * pageSize;
        for (int i = 1; i <= pageSize && n < page.getTotal(); i++) {
            list2.add(restaurantList.get(n));
            System.out.println(restaurantList.get(n).name);
            n++;
        }
        page.setItem(list2);
        return Result.success(page);

}
//查询餐厅
    @RequestMapping("/restaurant/searchR")
    public Result<?> find2(int currentPage,int pageSize,String search)
    {
       List<Restaurant> list= restaurantService.search(search);
        Page<Restaurant> page=new Page<>(list.size(),pageSize,currentPage);
        List<Restaurant> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);
        System.out.println("执行！！！！！！！！");
        return Result.success(page);
    }
}
