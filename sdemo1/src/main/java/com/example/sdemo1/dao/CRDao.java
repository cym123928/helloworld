package com.example.sdemo1.dao;

import org.apache.ibatis.annotations.*;
import com.example.sdemo1.pojo.*;

import java.util.List;

@Mapper
public interface CRDao {

 @Insert(" insert into  cr(number,foodname,phone,orderNumber) values(#{number},#{foodname},#{phone},0) ")
 int addC(int number,String foodname,String phone);

 @Select("select * from cr where  number=#{number} and foodname=#{foodname} and phone=#{phone}")
 CR find(int number, String phone, String foodname);

 @Update("update cr set orderNumber=#{orderNumber} where number=#{number}  and phone=#{phone}")
 int updateC(int number,int orderNumber,String phone);

 @Select("select * from cr where number=#{number} and phone=#{phone} and orderNumber=#{num}")
 List<CR> findMy(String phone,int number,int num);

 @Delete("delete from cr where number=#{number} and foodname=#{foodname} and phone=#{phone} and orderNumber=0")
 int deleteC(int number,String foodname,String phone);

 @Select("select * from cr where number=#{number}")
 List<CR> findAll(int number);    //查找餐厅的所有订单

 @Select("select distinct number,foodname,phone,orderNumber from cr natural join orderlist where orderNumber=#{orderNumber}")
 List<CR> findOD(int orderNumber);   //查看订单详情

 @Select("select * from deliver where status=#{status}")
 List<Deliver> findD(int status);


}
