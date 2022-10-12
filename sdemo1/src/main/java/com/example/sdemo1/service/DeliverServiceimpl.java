package com.example.sdemo1.service;

import com.example.sdemo1.dao.DeliverDao;
import com.example.sdemo1.pojo.Deliver;
import com.example.sdemo1.pojo.OL;
import com.example.sdemo1.pojo.Restaurant;
import com.example.sdemo1.pojo.SR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeliverServiceimpl implements DeliverService {
    @Resource
    DeliverDao deliverDao;
    @Override
    public int addD(Deliver deliver) {
        return deliverDao.addD(deliver);
    }

    @Override
    public int updateD(Deliver deliver) {
        return deliverDao.updateD(deliver);
    }

    @Override
    public int[] findD(int status, String phone) {
        return deliverDao.findD(status,phone);
    }

    @Override
    public Restaurant findByN(int number) {
        return deliverDao.findByN(number);
    }

    @Override
    public int updateDD(int orderNumber, String phone,int status) {
        return deliverDao.updateDD(orderNumber, phone,status);
    }

    @Override
    public int updateDDD(int orderNumber, int status) {
        return deliverDao.updateDDD(orderNumber,status);
    }

    @Override
    public int[] findON(int status, String phone) {
        return deliverDao.findON(status,phone);
    }

    @Override
    public OL selectOL(int status, int number) {
        return deliverDao.selectOL(status, number);
    }

    @Override
    public SR selectOD(int orderNumber) {
        return deliverDao.selectOD(orderNumber);
    }
}
