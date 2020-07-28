package com.car.dao;

import com.car.bean.CarInfo;

import java.util.HashMap;
import java.util.List;

public interface CarInfoDao {

    public List<CarInfo> getCarInfo(HashMap<String,Object> condition, int page);

    public int findCount(HashMap<String, Object> condition);

}
