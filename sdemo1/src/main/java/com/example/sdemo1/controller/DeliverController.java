package com.example.sdemo1.controller;

import com.example.sdemo1.pojo.*;
import com.example.sdemo1.service.DeliverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RestController
public class DeliverController {
    SR sr = null;
    UD ud = null;
    OL ol = null;
    Restaurant restaurant;
    @Resource
    private DeliverService deliverService;
    @RequestMapping("/deliver/add")
    public Result<?> add(Deliver deliver){
        System.out.println("a"+deliver.phone);
        deliverService.addD(deliver);
        System.out.println(deliver.phone);
        return Result.success("添加成功！");
    }

    @RequestMapping("/deliver/update")
    public Result<?> update(Deliver deliver){
        deliverService.updateD(deliver);
        return Result.success("更新成功！");
    }

    @RequestMapping("/deliver/updateDD")
    public Result<?> updateDD(int orderNumber,String phone,int status){
        System.out.println("updateDD");
        deliverService.updateDD(orderNumber, phone,status);
        deliverService.updateDDD(orderNumber,status);
        return Result.success("更新两张表成功！");
    }

    @RequestMapping("/deliver/updateDDD")
    public Result<?> updateDDD(int orderNumber,String phone,int status){
        System.out.println("updateDDD");
        deliverService.updateDD(0, phone,status);
        deliverService.updateDDD(orderNumber,status);
        return Result.success("更新两张表成功！");
    }

    @RequestMapping("/deliver/selectEd") //查询已经完成的订单
    public Result<?> selectEd(String phone,int pageSize,int currentPage){
        System.out.println("/deliver/selectEd");
      /*   int[] num = deliverService.findD(2,phone);  //餐厅号
        List<SR> list = new ArrayList<>();
        for(int i=0;i< num.length;i++){
            System.out.println("num"+num[i]);
           restaurant = deliverService.findByN(num[i]);
           if (restaurant.name!=null && restaurant.number!=0){
               sr = new SR();
               sr.setBoss(restaurant.boss);
               sr.setNumber(restaurant.number);
               sr.setCity(restaurant.city);
               sr.setName(restaurant.name);
               if(!list.contains(sr)){
               list.add(sr);
               }
               System.out.println("for"+sr.city);
           }
        }
        for(int i=0;i<list.size();i++){
            System.out.println(list.size());
            System.out.println(list.get(i).city);
        }*/
        int[] num = deliverService.findON(2,phone);
        List<UD> list = new ArrayList<>();
        for(int i=0; i<num.length; i++){
            ud = new UD();
            System.out.println("numselectEd"+num[i]);
            sr = deliverService.selectOD(num[i]);
            ud.number = sr.number;
            ud.name = sr.name;
            System.out.println("rname"+sr.name);
            ud.orderNumber = num[i];
            ud.city = sr.city;  //餐馆地址
            ud.boss = sr.boss;
            ol = deliverService.selectOL(2,num[i]);
            System.out.println("ol"+ol.address);
            ud.address = ol.address;
            ud.orderTime = ol.orderTime;
            ud.sendTime = ol.sendTime;
            ud.price = ol.price;
            if (!list.contains(ud))
            {
                list.add(ud);
            }
        }


      /*  Page<SR> page=new Page<>(list.size(),pageSize,currentPage);
        List<SR> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);
        return Result.success(page);*/
        Page<UD> page=new Page<>(list.size(),pageSize,currentPage);
        List<UD> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);
        System.out.println("/deliver/selectEd");
        return Result.success(page);
    }

}
