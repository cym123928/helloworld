package com.example.sdemo1.dao;

import com.example.sdemo1.pojo.Answer;
import com.example.sdemo1.pojo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnswerDao {
    @Insert("insert into answer(number,response,name,phone,type,time,count) values (#{number},#{response},#{name},#{phone},#{type},#{time},0)")
    int add(Answer answer);
    @Delete("delete from answer where id=#{id}")
    int delete(Answer answer);
    @Update("update answer set count=#{count} where id=#{id}")
    int updateCount(Answer answer);
    @Update("update answer set response=#{response},time=#{time} where id=#{id}")
    int update(Answer answer);
    @Select("select * from answer where phone=#{phone}")
    List<Answer> findByPhone(String phone);
    @Select("select * from answer where number=#{number}")
    List<Answer> findByQuestion(int number);
    @Select("select * from answer where response like concat('%',#{word},'%') and number=#{number}")
    List<Answer> search(String word,int number);
    @Select("select * from answer where phone=#{phone} and number=#{number}")
    List<Answer> findMine(String phone,int number);
}
