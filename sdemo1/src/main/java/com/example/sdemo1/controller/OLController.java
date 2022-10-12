package com.example.sdemo1.controller;

import com.example.sdemo1.pojo.*;
import com.example.sdemo1.service.CRService;
import com.example.sdemo1.service.OLService;
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
public class OLController {
    SR sr=null;
    UD ud =null;
    OL ol = null;
    User user = null;
    OL ol1 = null;
    ResDe resDe;
    @Resource
    private OLService olService;
    private CRService crService;

    @RequestMapping("/OL/add")
    public Result<?> add(int status, String orderTime, String address,int number,String phone){
        olService.addOL(status, orderTime, address);
       int max = olService.selectMax();
       System.out.println("max;"+max);
        List<CR>  list = olService.findM(phone,number);  //查找我的购买订单
        for (int i=0;i<list.size();i++){
            if(list.get(i).orderNumber == 0){
                list.get(i).orderNumber = max;
                olService.updatC(number,max,phone);
                System.out.println("max="+max);
            }
        }
        System.out.println("updateOLL"+max);
        double price = olService.selectSum(max);
        olService.updateOLL(price,max);
        System.out.println(price+"....price");
        return Result.success("添加成功！");

    }

    @RequestMapping("/OL/price")
    public Result<?> selectSum(){
       double price = olService.selectSum(0);
       return Result.success(price);
    }


    @RequestMapping("/OL/send")
    public Result<?> send(int status,int number,int pageSize,int currentPage){
      List<OL> list = olService.send(status,number);
      List<ResDe> list1 = new ArrayList<>();
      resDe = new ResDe();
      ol1 = new OL();
      String phone="";
      for(int i=0; i<list.size();i++){
          System.out.println(list.size()+"size");
          user = olService.selectD(list.get(i).orderNumber,status);
          phone = olService.selectU(list.get(i).orderNumber);
          System.out.println(list.get(i).orderNumber+"orderNumber");
          ol1 = list.get(i);
        //  resDe.orderNumber = list.get(i).orderNumber;
          resDe.orderNumber = ol1.orderNumber;
          System.out.println(resDe.orderNumber+"resorederNumer");
         /* resDe.orderTime = list.get(i).orderTime;
          resDe.sendTime = list.get(i).sendTime;
          resDe.address = list.get(i).address;
          resDe.price = list.get(i).price;*/
          resDe.orderTime = ol1.orderTime;
          resDe.sendTime = ol1.sendTime;
          resDe.address = ol1.address;
          resDe.price = ol1.price;
          resDe.phoneD = user.phone;
          resDe.nameD = user.name;
          resDe.phoneC = phone;
          if(!list1.contains(resDe)){
              list1.add(resDe);
          }
      }
        Page<ResDe> page=new Page<>(list1.size(),pageSize,currentPage);
        List<ResDe> list2=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list2.add(list1.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list2);
        System.out.println("OL/SEND");
        return Result.success(page);
    }

    @RequestMapping("/OL/send1")
    public Result<?> send1(int status,int number,int pageSize,int currentPage){
        List<OL> list = olService.send(status,number);
        Page<OL> page=new Page<>(list.size(),pageSize,currentPage);
        List<OL> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);
        System.out.println("OL/SEND");
        return Result.success(page);
    }



    @RequestMapping("/OL/myOrdered")
    public Result<?> send(int status,String phone,int pageSize,int currentPage) {
         int [] num = olService.selectO(status,phone);
         List<UD> list = new ArrayList<>();
         for(int i=0;i<num.length;i++) {
             ud = new UD();
             System.out.println("numordered" + num[i]);
             sr = olService.selectOD(num[i]);
             System.out.println(sr.number+"srnumber");
             ud.setNumber(sr.number);
             ud.name = sr.name;
             System.out.println("rname"+sr.name);
             ud.orderNumber = num[i];
             ud.city = sr.city;  //餐馆地址
             ud.boss = sr.boss;
             ol = olService.selectOL(status,num[i]);
             ud.address = ol.address;
             ud.orderTime = ol.orderTime;
             ud.sendTime = ol.sendTime;
             ud.price = ol.price;
             if (!list.contains(ud))
             {
                 list.add(ud);
             }
         }
        Page<UD> page=new Page<>(list.size(),pageSize,currentPage);
        List<UD> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);
        System.out.println("OL/myOrdered");
        return Result.success(page);
    }

    @RequestMapping("/OL/confirm")
    public Result<?> confirm(int orderNumber,String sendTime){
        olService.updateOL(2,orderNumber,sendTime);
        olService.updateDeliver(orderNumber,2);
        System.out.println("ol/confirm");
        return Result.success("更新成功！");
    }

    @RequestMapping("/OL/cancel")
    public Result<?> cancel(int orderNumber){
       olService.deleteOL(orderNumber);
       olService.deleteCR(orderNumber);
       olService.updateDeliver(orderNumber,0);
        System.out.println("ol/confirm");
        return Result.success("更新成功！");
    }

}
