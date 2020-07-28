package com.car.service.impl;

import com.car.bean.CarInfo;
import com.car.bean.PageBean;
import com.car.dao.CarInfoDao;
import com.car.service.CarInfoService;
import com.car.util.ObjectFactory;

import java.util.HashMap;
import java.util.List;

public class CarInfoServiceImpl implements CarInfoService {

    CarInfoDao carInfoDao= (CarInfoDao) ObjectFactory.getObject("com.car.dao.impl.CarInfoDaoImpl");

    @Override
    public PageBean<CarInfo> getCarInfo(HashMap<String, Object> condition, int curPage, int pageSize) {
        List<CarInfo> list=carInfoDao.getCarInfo(condition,curPage);

        int totalRecord=carInfoDao.findCount(condition);

        PageBean<CarInfo> pageBean=new PageBean<>(curPage,pageSize,totalRecord);
        pageBean.setList(list);

        return pageBean;
    }
}
