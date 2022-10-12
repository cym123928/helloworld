package com.example.sdemo1.controller;

import com.example.sdemo1.pojo.*;
import com.example.sdemo1.service.CRService;
import com.example.sdemo1.service.CommentService;
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
public class CommentController {
     CommentR commentR = null;
     OL ol = null;
     Comment comment = null;
    @Resource
    private CommentService commentService;

    @RequestMapping("/CC/addC")
    public Result<?> addC(Comment comment) {
        commentService.addC(comment);
        System.out.println("/CC/addC");
        return Result.success("添加成功！");
    }

    @RequestMapping("/CC/selectC")
    public Result<?> selectC(int orderNumber) {
       List <Comment> list = commentService.selectCom1( orderNumber);
      /*  Page<Comment> page=new Page<>(list.size(),pageSize,currentPage);
        List<Comment> list1=new ArrayList<>();
        int n=(currentPage-1)*pageSize;
        for(int i=1;i<=pageSize&&n<page.getTotal();i++)
        {
            list1.add(list.get(n));
            n=(currentPage-1)*pageSize+i;
        }
        page.setItem(list1);*/
        System.out.println("/CC/selectC");
       // System.out.println(list.get(0).delCom1);
        return Result.success(list);
    }

    //选出对餐厅的评价
    @RequestMapping("/CC/selectCR")
    public Result<?> selectCR(int number,int pageSize,int currentPage) {
        int[] num = commentService.selectON(number);

        //ol = new OL();
        String name="";
        String phone = "";
        List<CommentR> list = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            commentR = new CommentR();
            System.out.println("numi"+num[i]);
            ol = commentService.selectOL(num[i]);
            phone = commentService.selectPhone(num[i]);
            name = commentService.selectName(phone);
            comment = commentService.selectComR(num[i]);
            commentR.name = name;
            commentR.phone = phone;
            commentR.address = ol.address;
            commentR.orderTime = ol.orderTime;
            commentR.sendTime = ol.sendTime;
            commentR.price = ol.price;
            commentR.resCom = comment.resCom;
            commentR.resCom1 = comment.resCom1;
            commentR.orderNumber = ol.orderNumber;
            if (!list.contains(commentR)) {
                list.add(commentR);
            }
        }
        System.out.println(list.size()+"size()");
            Page<CommentR> page=new Page<>(list.size(),pageSize,currentPage);
            List<CommentR> list1=new ArrayList<>();
            int n=(currentPage-1)*pageSize;
            for(int i=1;i<=pageSize&&n<page.getTotal();i++)
            {
                list1.add(list.get(n));
                n=(currentPage-1)*pageSize+i;
            }
            page.setItem(list1);
            System.out.println("CC/selectCR");
            return Result.success(page);
    }
}
