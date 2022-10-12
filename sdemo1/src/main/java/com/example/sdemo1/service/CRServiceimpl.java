package com.example.sdemo1.service;

import com.example.sdemo1.dao.CRDao;
import com.example.sdemo1.pojo.CR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CRServiceimpl implements CRService {
    @Resource
    CRDao crDao;
    @Override
    public int addC(int number, String foodname, String phone) {
        return crDao.addC(number,foodname,phone);
    }

    @Override
    public CR find(int number, String phone, String foodname) {
        return crDao.find(number,phone,foodname);
    }


    @Override
    public List<CR> findMy(String phone, int number) {
        System.out.println("调用了findMy");
        System.out.println(phone+"餐厅名"+number);
        int num=0;
        return crDao.findMy(phone,number,0);
    }

    @Override
    public int deleteC(int number, String foodname, String phone) {
        return crDao.deleteC(number,foodname,phone);
    }

    @Override
    public int updateC(int number,  int orderNumber, String phone) {
        return crDao.updateC(number,orderNumber,phone);
    }

    @Override
    public List<CR> findAll(int number) {
        return crDao.findAll(number);
    }

    @Override
    public List<CR> findOD(int orderNumber) {
        return crDao.findOD(orderNumber);
    }
}
