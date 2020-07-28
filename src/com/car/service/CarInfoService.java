package com.car.service;

import com.car.bean.CarInfo;
import com.car.bean.PageBean;

import java.util.HashMap;

public interface CarInfoService {

    public PageBean<CarInfo> getCarInfo(HashMap<String,Object> condition, int curPage, int pageSize);

}
