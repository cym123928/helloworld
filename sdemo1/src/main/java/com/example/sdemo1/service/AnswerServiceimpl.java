package com.example.sdemo1.service;

import com.example.sdemo1.dao.AnswerDao;
import com.example.sdemo1.pojo.Answer;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
@Service
public class AnswerServiceimpl implements AnswerService{
    @Resource
    private AnswerDao answerDao;
    @Override
    public int add(Answer answer) {
        return answerDao.add(answer);
    }

    @Override
    public int delete(Answer answer) {
        return answerDao.delete(answer);
    }

    @Override
    public int updateCount(Answer answer) {
        answer.count++;
        return answerDao.updateCount(answer);
    }

    @Override
    public int update(Answer answer) {
        return answerDao.update(answer);
    }

    @Override
    public List<Answer> findByPhone(String phone) {
        List<Answer> list= answerDao.findByPhone(phone);
        System.out.println(list.get(0).number);
        return list;
    }

    @Override
    public List<Answer> findByQuestion(int number) {
        return answerDao.findByQuestion(number);
    }

    @Override
    public List<Answer> search(String word,int number) {
        return answerDao.search(word,number);
    }

    @Override
    public List<Answer> findMine(String phone, int number) {
        return answerDao.findMine(phone,number);
    }
}
