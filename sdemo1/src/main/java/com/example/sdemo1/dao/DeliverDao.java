package com.example.sdemo1.dao;

import com.example.sdemo1.pojo.Deliver;
import com.example.sdemo1.pojo.OL;
import com.example.sdemo1.pojo.Restaurant;
import com.example.sdemo1.pojo.SR;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeliverDao {

    @Insert(" insert into deliver(phone,status,orderNumber) values (#{phone},#{status},#{orderNumber})")
    int addD(Deliver deliver);
    @Update(" update deliver set status=#{status} and orderNumber=0 where phone=#{phone} and orderNumber=#{orderNumber}")
    int updateD(Deliver deliver);   //外卖送完更新状态

    @Update("update deliver set status=#{status},orderNumber=#{orderNumber} where phone=#{phone}")
    int updateDD(int orderNumber,String phone,int status);  //外卖员接收外卖状态改变

    @Update("update orderlist a set a.status=#{status} where a.orderNumber=#{orderNumber}")
    int updateDDD(int orderNumber,int status);  //外卖员接收外卖状态改变

    @Select("select distinct b.number from deliver a join cr b on a.orderNumber=b.orderNumber where a.status=#{status} and a.phone=#{phone}")
    int[] findD(int status, String phone);

    //外卖员查看订单详情
    @Select("select distinct orderNumber from deliver where status=#{status} and phone=#{phone}")
    int[] findON(int status,String phone);  //查找某外卖员某种状态的订单号
    @Select("select distinct orderNumber,status,orderTime,sendTime,price,address from orderlist where status=#{status} and orderNumber=#{number}")
    OL selectOL(int status, int number);   //查找订单信息  根据订单号
    @Select("select distinct number,name, boss, city from cr natural join restaurant where orderNumber=#{orderNumber}")
    SR selectOD(int orderNumber);      //根据订单号查找餐厅名等信息

    @Delete("delete from deliver where phone=#{phone} and orderNumber=#{orderNumber} and status=#{status}")
    int deleteD(Deliver deliver);

    @Select("select distinct number,name, boss,city,scale from restaurant where number=#{number}")
    Restaurant findByN(int number);


}
