package com.example.sdemo1.dao;

import com.example.sdemo1.pojo.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RestaurantDao {
    @Insert(" insert into  restaurant( name,boss,city,scale ) values (#{name},#{boss},#{city},#{scale}) ")
    int addR(Restaurant restaurant);
    @Delete(" delete  from restaurant where name=#{name}")
    int deleteR(Restaurant restaurant);
    @Delete(" delete from restaurant where name=#{name} and foodname=#{foodname}")
    int deleteF(Restaurant restaurant);


    @Select("select * from restaurant where boss=#{boss}")
    List<Restaurant> collect(String phone);


    @Select("select * from restaurant where number=#{number}")
    Restaurant find(int number);

    @Select("select * from restaurant where name like concat('%',#{word},'%')")
    List<Restaurant> search(String word);

    @Select("select * from restaurant")
    List<Restaurant> all();

}
