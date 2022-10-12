package com.example.sdemo1.service;

import com.example.sdemo1.pojo.Comment;
import com.example.sdemo1.pojo.OL;

import java.util.List;

public interface CommentService {
    int addC(Comment comment);
    int updateC(Comment comment);
    List<Comment> selectCom(String phone,int orderNumber);   //查询用户的评价
    Comment selectComR(int orderNumber);
    int[] selectON(int number);
    String selectPhone(int orderNumber);
    String selectName(String phone);
    OL selectOL(int orderNumber);
    List<Comment> selectCom1(int orderNumber);
}

