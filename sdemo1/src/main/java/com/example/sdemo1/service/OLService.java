package com.example.sdemo1.service;

import com.example.sdemo1.pojo.CR;
import com.example.sdemo1.pojo.OL;
import com.example.sdemo1.pojo.SR;
import com.example.sdemo1.pojo.User;

import java.util.List;

public interface OLService {
   // int addOL(OL ol);
   int addOL(int status,String orderTime,String address);
    int updateOL(int status, int orderNumber,String sendTime);

    int updateDeliver(int orderNumber,int status);

    int selectMax();
 //   List<OL> unSend();
 //   List<OL> inSend();
  //  List<OL> sended();
    List<OL> send(int status,int number);
    double selectSum(int orderNumber);
    int updateOLL(double price,int orderNumber);
    List<CR> findM(String phone, int number);
    int updatC(int number,int orderNumber,String phone);
    int[] selectO(int status,String phone);
    SR selectOD(int orderNumber);
    OL  selectOL(int status,int number);

    int deleteOL(int orderNumber);
    int deleteCR(int orderNumber);
    User selectD(int orderNumber, int status);
    String selectU(int orderNumber);

}
