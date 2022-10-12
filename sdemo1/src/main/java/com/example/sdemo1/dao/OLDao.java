package com.example.sdemo1.dao;

import com.example.sdemo1.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OLDao {
    @Insert(" insert into orderlist(status,orderTime,address) values (#{status},#{orderTime},#{address})")
    int addOL(int status,String orderTime,String address);

    @Update("update orderlist set status=#{status},sendTime=#{sendTime} where orderNumber=#{orderNumber}")
    int updateOL(int status,int orderNumber,String sendTime);

    @Update("update deliver set status=#{status} where orderNumber=#{orderNumber}")
    int updateDeliver(int orderNumber,int status);

   /* @Update("update orderlist set sendTime=#{sendTime} where orderNumber=#{orderNumber}")
    int updateOLT(String sendTime);*/

    @Select("select max(orderNumber) from orderlist ")
    int selectMax();

    @Select("select sum(price) from cr,food where cr.foodname = food.name and cr.number = food.number and orderNumber=#{orderNumber}")
    double selectSum(int orderNumber);

    @Update("update orderlist set price=#{price} where orderNumber=#{orderNumber}")
    int updateOLL(double price,int orderNumber);

   @Select("select distinct orderNumber,status,orderTime,sendTime,price,address from orderlist natural join cr where status=#{status} and number=#{number}")
    List<OL> send(int status,int number);   //显示餐厅的订单信息
    @Select("select distinct phone,name,password from deliver natural join user where orderNumber=#{orderNumber} and status=#{status}")
    User selectD(int orderNumber, int status);   //选出外卖员姓名，联系方式
    @Select("select distinct phone from cr where orderNumber=#{orderNumber}")
    String selectU(int orderNumber);  //选出用户联系方式




    @Select("select distinct orderNumber,status,orderTime,sendTime,price,address from orderlist where status=#{status} and orderNumber=#{number}")
    OL selectOL(int status,int number);

    @Select("select * from cr where number=#{number} and phone=#{phone}")
    List<CR> findM(String phone, int number);

    @Update("update cr set orderNumber=#{orderNumber} where number=#{number}  and phone=#{phone} and orderNumber=0")
    int updatC(int number,int orderNumber,String phone);

    @Select("select distinct orderNumber from cr natural join orderlist where status=#{status} and phone=#{phone}")
    int[] selectO(int status,String phone);   //根据状态查找用户的订单号

    @Select("select distinct number,name, boss, city from cr natural join restaurant where orderNumber=#{orderNumber}")
    SR selectOD(int orderNumber);  //根据用户的订单号查询用户的具体信息

    //取消订单的操作:
    @Delete("delete from orderlist where orderNumber=#{orderNumber}")
    int deleteOL(int orderNumber);
    @Delete("delete from cr where orderNumber=#{orderNumber}")
    int deleteCR(int orderNumber);
//用于显示商家查看订单的信息




}
