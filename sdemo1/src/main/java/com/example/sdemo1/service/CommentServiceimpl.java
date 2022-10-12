package com.example.sdemo1.service;

import com.example.sdemo1.dao.CommentDao;
import com.example.sdemo1.pojo.Comment;
import com.example.sdemo1.pojo.OL;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceimpl implements CommentService{
    @Resource
    CommentDao commentDao;

    @Override
    public int addC(Comment comment) {
        return commentDao.addC(comment);
    }

    @Override
    public int updateC(Comment comment) {
        return commentDao.updateC(comment);
    }

    @Override
    public List<Comment> selectCom(String phone,int orderNumber) {
        return commentDao.selectCom(phone,orderNumber);
    }

    @Override
    public Comment selectComR(int orderNumber) {
        return commentDao.selectComR(orderNumber);
    }

    @Override
    public int[] selectON(int number) {
        return commentDao.selectON(number);
    }

    @Override
    public String selectPhone(int orderNumber) {
        return commentDao.selectPhone(orderNumber);
    }

    @Override
    public String selectName(String phone) {
        return commentDao.selectName(phone);
    }

    @Override
    public OL selectOL(int orderNumber) {
        return commentDao.selectOL(orderNumber);
    }

    @Override
    public List<Comment> selectCom1(int orderNumber) {
        return commentDao.selectCom1(orderNumber);
    }
}
