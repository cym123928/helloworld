package com.example.sdemo1.dao;
import com.example.sdemo1.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    @Insert(" insert into  user( name,phone,password ) values (#{name},#{phone},#{password}) ")
    int addU(User user);
    @Delete(" delete from user where phone= #{phone}")
    int deleteU(User user);
    @Update("update user set name=#{name},password=#{password} where phone=#{phone}")
    int updateU(User user);
    @Select("select * from user where phone=#{phone}")
    User findByPhone(String phone);
    @Select("select * from user where name=#{name}")
    User findByName(String name);
}
