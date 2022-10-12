package com.example.sdemo1.service;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.sdemo1.pojo.Collection;
import com.example.sdemo1.pojo.Question;
import com.example.sdemo1.pojo.User;

import java.util.List;

public interface QuestionService{
    int addQ(Question question);
    int deleteQ(Question question);
    int updateQ(Question question);
    int updateCount(Question question);
    Question find(int number);
    List<Question> search1(String phone,String word,int value);
    List<Question> findByPhone(String phone,int value);
    List<Question> findValued();
    List<Question> findUndeal();
    List<Question> findUnvalued();
    List<Question> search(String word,int value);
    List<Question> top(String start,String end);
    List<Collection> collect(String phone);

}
