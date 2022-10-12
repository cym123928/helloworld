package com.example.sdemo1.service;

import com.example.sdemo1.dao.OLDao;
import com.example.sdemo1.pojo.CR;
import com.example.sdemo1.pojo.OL;
import com.example.sdemo1.pojo.SR;
import com.example.sdemo1.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OLServiceimpl implements OLService{
    @Resource
    OLDao olDao;
   /* @Override
    public int addOL(OL ol) {
        return olDao.addOL(ol);
    }*/

    @Override
    public int addOL(int status, String orderTime, String address) {
        return olDao.addOL(status, orderTime, address);
    }

    @Override
    public int updateOL(int status,int orderNumber,String sendTime) {
        return olDao.updateOL(status, orderNumber,sendTime);
    }

    @Override
    public int updateDeliver(int orderNumber,int status) {
        return olDao.updateDeliver(orderNumber,status);
    }

    @Override
    public int selectMax() {
        return olDao.selectMax();
    }

    @Override
    public List<OL> send(int status,int number) {
        return olDao.send(status,number);
    }

    @Override
    public double selectSum(int orderNumber) {
        return olDao.selectSum(orderNumber);
    }

    @Override
    public int updateOLL(double price, int orderNumber) {
        return olDao.updateOLL(price,orderNumber);
    }

    @Override
    public List<CR> findM(String phone, int number) {
        return olDao.findM(phone,number);
    }

    @Override
    public int updatC(int number, int orderNumber, String phone) {
        return olDao.updatC(number, orderNumber, phone);
    }

    @Override
    public int[] selectO(int status, String phone) {
        return olDao.selectO(status,phone);
    }

    @Override
    public SR selectOD(int orderNumber) {
        return olDao.selectOD(orderNumber);
    }

    @Override
    public OL selectOL(int status, int number) {
        return olDao.selectOL(status, number);
    }

    @Override
    public int deleteOL(int orderNumber) {
        return olDao.deleteOL(orderNumber);
    }

    @Override
    public int deleteCR(int orderNumber) {
        return olDao.deleteCR(orderNumber);
    }

    @Override
    public User selectD(int orderNumber, int status) {
        return olDao.selectD(orderNumber, status);
    }

    @Override
    public String selectU(int orderNumber) {
        return olDao.selectU(orderNumber);
    }

  /*  @Override
    public List<OL> unSend() {
        return olDao.unSend();
    }

    @Override
    public List<OL> inSend() {
        return olDao.inSend();
    }

    @Override
    public List<OL> sended() {
        return olDao.sended();
    }*/
}
