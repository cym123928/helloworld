package com.example.sdemo1.service;

import com.example.sdemo1.pojo.User;
import java.util.List;

public interface UserService {
    int addU(User user);
    int deleteU(User user);
    int updateU(User user);
    User findByPhone(String phone);
    User findByName(String name);
}
