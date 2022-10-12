package com.example.sdemo1.dao;
import com.example.sdemo1.pojo.Collection;
import com.example.sdemo1.pojo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionDao{
    @Insert(" insert into  question( content,name,phone,time,value) values (#{content},#{name},#{phone},#{time}, 0) ")
    int addQ(Question question);
    @Delete(" delete from question where number=#{number}")
    int deleteQ(Question question);
    @Update("update question set value=#{value},count=0 where number=#{number}")
    int updateQ(Question question);
    @Update("update question set count=#{count} where number=#{number}")
    int updateCount(Question question);
    @Select("select * from question where content like concat('%',#{word},'%') and phone=#{phone} and value=#{value}")
    List<Question> search1(String phone,String word,int value);

    @Select("select * from question where phone=#{phone} and value=#{value}")
    List<Question> findByPhone(String phone,int value);

    @Select("select * from question where value=1")
    List<Question> findValued();
    @Select("select * from question where value=0")
    List<Question> findUndeal();
    @Select("select * from question where value=2")
    List<Question> findUnvalued();
    @Select("select * from question where content like concat('%',#{word},'%') and value=#{value}")
    List<Question> search(String word,int value);
    @Select("select * from question where number=#{number}")
    Question find(int number);
    @Select("select * from question where value=1 and time between #{start} and #{end}")
    List<Question> top(String start,String end);
    @Select("select * from collection where phone=#{phone}")
    List<Collection> collect(String phone);
}
