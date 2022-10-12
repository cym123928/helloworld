package com.example.sdemo1.service;

import com.example.sdemo1.dao.UserDao;
import com.example.sdemo1.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceimpl implements UserService{
    @Resource
    UserDao userDao;
    @Override
    public int addU(User user) {
        return userDao.addU(user);
    }

    @Override
    public int deleteU(User user) {
        return userDao.deleteU(user);
    }

    @Override
    public int updateU(User user) {
        return userDao.updateU(user);
    }

    @Override
    public User findByPhone(String phone) {
        return userDao.findByPhone(phone);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name) ;
    }
}
