package com.example.sdemo1.dao;

import com.example.sdemo1.pojo.Food;
import com.example.sdemo1.pojo.Restaurant;
import com.example.sdemo1.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FoodDao {
    @Insert(" insert into  food( name,number,price) values (#{name},#{number},#{price}) ")
    int addF(Food food);
    @Delete(" delete from food where name=#{name} and number=#{number}")
    int deleteF(Food food);
    @Select("select * from food where number=#{name}")
    List<Food> findByRName(int name);   //查询餐厅所有食物

    @Select("select * from food where name=#{name}")
    Food find(String name);

    @Update("update food set price=#{price} where name=#{name} and number=#{number}")
    int updateF(Food food);

    @Select("select * from food where name like concat('%',#{name},'%') and number=#{number}")
    List<Food> search(String name,int number);
}
