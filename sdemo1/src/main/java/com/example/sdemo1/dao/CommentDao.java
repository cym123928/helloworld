package com.example.sdemo1.dao;

import com.example.sdemo1.pojo.Comment;
import com.example.sdemo1.pojo.Food;
import com.example.sdemo1.pojo.OL;
import com.example.sdemo1.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentDao {
    @Insert(" insert into  comment( orderNumber,resCom,delCom,resCom1,delCom1 ) values (#{orderNumber},#{resCom},#{delCom},#{resCom1},#{delCom1}) ")
    int addC(Comment comment);
    @Update("update comment set resCom=#{resCom},delCom=#{delCom},resCom1= #{resCom1},delCom1=#{delCom1} where orderNumber=#{orderNumber}")
    int updateC(Comment comment);

    @Select("select distinct orderNumber,resCom,delCom,resCom1,delCom1 from comment natural join deliver where phone=#{phone}")
    List<Comment> selectCom(String phone,int orderNumber);

    @Select("select distinct orderNumber,resCom,delCom,resCom1,delCom1 from comment where orderNumber=#{orderNumber}")
    List<Comment> selectCom1(int orderNumber);  //用户查看某订单的评价

    @Select("select distinct phone from cr where orderNumber=#{orderNumber}")
    String selectPhone(int orderNumber);  //用户联系方式
    @Select("select distinct name from user where phone=#{phone}")
    String selectName(String phone);   //用户名
    @Select("select orderNumber,status,orderTime,sendTime,address,price from orderlist where orderNumber=#{orderNumber} and status=2")
    OL selectOL(int orderNumber);
    @Select("select distinct orderNumber,resCom,delCom,resCom1,delCom1 from comment where orderNumber=#{orderNumber}")
    Comment selectComR(int orderNumber);  //根军订单号查找所有的评论
    @Select("select distinct orderNumber from cr natural join orderlist where number=#{number} and status=2")
    int[] selectON(int number);   //找出餐厅的所有订单号


}
