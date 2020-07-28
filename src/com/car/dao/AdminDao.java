package com.car.dao;



import com.car.bean.Admin;

import java.util.HashMap;
import java.util.List;

public interface AdminDao {

    public Admin login(String account, String psd);

    public int findCount(HashMap<String,Object> condition);

    public List<Admin> findUserByPageNum(HashMap<String,Object> condition, int page);

    public int changeState(HashMap<String,Object> condition);

    public int resetPsd(HashMap<String,Object> condition);

    public int delUser(HashMap<String,Object> condition);



}
