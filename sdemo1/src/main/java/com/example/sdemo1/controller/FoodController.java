package com.example.sdemo1.controller;

import com.example.sdemo1.pojo.Food;
import com.example.sdemo1.pojo.Page;
import com.example.sdemo1.pojo.Restaurant;
import com.example.sdemo1.pojo.Result;
import com.example.sdemo1.service.FoodService;
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
public class FoodController {
    @Resource
    private FoodService foodService;
    @RequestMapping("/food/add")
    public Result<?> add(Food food){
        System.out.println("AAA"+food.name);
        foodService.addF(food);
        System.out.println(food.name);
        return Result.success("添加成功！");
    }
   @RequestMapping("/food/delete")
    public Result<?> delete(Food food){
        foodService.deleteF(food);
        return Result.success("删除成功！");
    }

    @RequestMapping("/food/update")
    public Result<?> update(Food food){
        System.out.println("进入更新");
        foodService.updateF(food);
        return Result.success("更新成功！");
    }

    @RequestMapping("/food/findByRName")    //找这家餐厅的所有食物   需要餐厅的名称
    public Result<?> findByRF(int name,int currentPage,int pageSize) {
        System.out.println(currentPage+" "+pageSize+"bbb"+name);;
        int number = 0;
        List<Food> list = foodService.findByRName(name);   //list为找到的目标
        System.out.println(list.size());
        for(int i=0;i<list.size();i++)
        {
            number=list.get(i).number;
            System.out.println(number);;
        }
        Page<Food> page=new Page<>(list.size(),pageSize,currentPage);
        List<Food> list2=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list2.add(list.get(n));
            System.out.println(list.get(n).name);
            n++;
        }
        page.setItem(list2);
        return Result.success(page);
    }


    @RequestMapping("/food/find")
    public Result<?> find(String name,int number,int currentPage,int pageSize){
        System.out.println("名称"+name);
        List<Food> list = foodService.search(name,number);
        Page<Food> page = new Page<>(list.size(),pageSize,currentPage);
        List<Food> list1 = new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);

        System.out.println("执行方法了！！111！");

        return Result.success(page);
    }


}
