package com.car.service.impl;


import com.car.bean.Admin;
import com.car.bean.PageBean;
import com.car.dao.AdminDao;
import com.car.service.AdminService;
import com.car.util.ObjectFactory;

import java.util.HashMap;
import java.util.List;

public class AdminServiceImpl implements AdminService {


    AdminDao adminDao= (AdminDao) ObjectFactory.getObject("com.pcs.dao.impl.AdminDaoImpl");

    @Override
    public Admin login(String account, String psd) {
        System.out.println("AdminServiceImpl login ing...");
        return adminDao.login(account,psd);
    }

    @Override
    public int findUserAccount(String account) {
        return 0;
    }

    @Override
    public PageBean<Admin> findUsersByPage(HashMap<String, Object> condition, int curPage, int pageSize) {
        List<Admin> list=adminDao.findUserByPageNum(condition,curPage);

        int totalRecord=adminDao.findCount(condition);

        PageBean<Admin> pageBean=new PageBean<>(curPage,pageSize,totalRecord);
        pageBean.setList(list);

        return pageBean;
    }

    @Override
    public int changeState(HashMap<String, Object> condition) {
        return adminDao.changeState(condition);
    }

    @Override
    public int resetPsd(HashMap<String, Object> condition) {
        return adminDao.resetPsd(condition);
    }

    @Override
    public int delUser(HashMap<String, Object> condition) {
        return adminDao.delUser(condition);
    }


}
