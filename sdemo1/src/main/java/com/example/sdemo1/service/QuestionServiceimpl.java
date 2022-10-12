package com.example.sdemo1.service;
import com.example.sdemo1.pojo.Collection;
import com.example.sdemo1.pojo.Question;
import com.example.sdemo1.dao.QuestionDao;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;
@Service
public class QuestionServiceimpl implements QuestionService {
    @Resource
    private QuestionDao questionDao;
    @Override
    public int addQ(Question question) {
        return questionDao.addQ(question);
    }

    @Override
    public int deleteQ(Question question) {
       return questionDao.deleteQ(question);
    }

    @Override
    public int updateQ(Question question) {
        return questionDao.updateQ(question);
    }
    @Override
    public List<Question> findValued() {

        return questionDao.findValued();
    }
    @Override
    public List<Question> findUndeal() {
        return questionDao.findUndeal();
    }

    @Override
    public List<Question> findUnvalued() {
        return questionDao.findUnvalued();
    }
    @Override
    public List<Question> search(String word,int value) {
        return questionDao.search(word,value);
    }

    @Override
    public List<Question> top(String start, String end) {
        return questionDao.top(start,end);
    }

    @Override
    public List<Collection> collect(String phone) {
        return questionDao.collect(phone);
    }

    @Override
    public int updateCount(Question question) {
        question.count++;
        return questionDao.updateCount(question) ;
    }

    @Override
    public Question find(int number) {
        return questionDao.find(number);
    }

    @Override
    public List<Question> search1(String phone, String word, int value) {
        return questionDao.search1(phone,word,value);
    }

    @Override
    public List<Question> findByPhone(String phone, int value) {
        return questionDao.findByPhone(phone,value);
    }

}
