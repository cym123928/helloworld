package com.example.sdemo1.service;

import com.example.sdemo1.pojo.Deliver;
import com.example.sdemo1.pojo.OL;
import com.example.sdemo1.pojo.Restaurant;
import com.example.sdemo1.pojo.SR;

import java.util.List;

public interface DeliverService {
    int addD(Deliver deliver);
    int updateD(Deliver deliver);
    int[] findD(int status, String phone);
    Restaurant findByN(int number);
    int updateDD(int orderNumber,String phone,int status);
    int updateDDD(int orderNumber,int status);
    int[] findON(int status,String phone);
    OL selectOL(int status, int number);
    SR selectOD(int orderNumber);
}
