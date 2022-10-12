package com.example.sdemo1.controller;


import com.example.sdemo1.pojo.*;
import com.example.sdemo1.service.CRService;
import com.example.sdemo1.service.CRServiceimpl;
import com.example.sdemo1.service.OLService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CRController {
    @Resource
    private CRService crService;
    @RequestMapping("/CR/add")
    public Result<?> add(int number,String foodname,String phone){
        System.out.println("a");
      //  CR cr = crService.find(number,phone,foodname);
       // if(cr==null) {
            crService.addC(number, foodname, phone);
       // }
        System.out.println("crcr");
        return Result.success("添加成功！");
    }
    @RequestMapping("/CR/delete")
    public Result<?> delete(int number,String foodname,String phone){
        System.out.println("ad");
      //  CR cr = crService.find(number,phone,foodname);
       // if(cr!=null){
            crService.deleteC(number,foodname,phone);
        //}
        System.out.println("crcrdelete");
        return Result.success("删除成功！");
    }

    @RequestMapping("/CR/findMy")
    public Result<?> findMy(int number,String phone,int pageSize,int currentPage){
        System.out.println("abcdefg");
        List<CR> list = crService.findMy(phone,number);
        Page<CR> page=new Page<>(list.size(),pageSize,currentPage);
        List<CR> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);
        return Result.success(page);

    }

  /*  @RequestMapping("/CR/update")
    public Result<?> update(Integer number, String phone){
        System.out.println("max:"+max);
      List<CR>  list = crService.findMy(phone,number);  //查找我的购买订单
        for (int i=0;i<list.size();i++){
            if(list.get(i).orderNumber == 0){
                list.get(i).orderNumber = max;
                crService.updateC(number,max,phone);
                System.out.println("max="+max);
            }
        }
        return Result.success("更新成功！");
    }*/

    @RequestMapping("/CR/findAll")
    public Result<?> findMy(int number,int pageSize,int currentPage){
        System.out.println("/CR/findAll");
        List<CR> list = crService.findAll(number);
        Page<CR> page=new Page<>(list.size(),pageSize,currentPage);
        List<CR> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);
        return Result.success(page);
    }

    @RequestMapping("/CR/findOD")
    public Result<?> findMy(int orderNumber){
        System.out.println("/CR/findOD");
        List<CR> list = crService.findOD(orderNumber);
        return Result.success(list);
    }

}
