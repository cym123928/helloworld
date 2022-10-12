package com.example.sdemo1.service;

import com.example.sdemo1.pojo.CR;

import java.util.List;

public interface CRService {

    int addC(int number,String foodname,String phone);
    CR find(int number, String phone, String foodname);
    List<CR> findMy(String phone,int number);
    int deleteC(int number,String foodname,String phone);
    int updateC(int number,int orderNumber,String phone);
    List<CR> findAll(int number);
    List<CR> findOD(int orderNumber);
}
