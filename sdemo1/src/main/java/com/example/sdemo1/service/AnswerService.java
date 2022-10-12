package com.example.sdemo1.service;

import com.example.sdemo1.pojo.Answer;

import java.util.List;

public interface AnswerService {
    int add(Answer answer);
    int delete(Answer answer);
    int updateCount(Answer answer);
    int update(Answer answer);
    List<Answer> findByPhone(String phone);
    List<Answer> findByQuestion(int number);
    List<Answer> search(String word,int number);
    List<Answer> findMine(String phone,int number);
}
