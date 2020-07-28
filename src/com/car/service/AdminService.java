package com.car.service;



import com.car.bean.Admin;
import com.car.bean.PageBean;

import java.util.HashMap;

public interface AdminService {

    public Admin login(String account, String psd);

    public int findUserAccount(String account);

    public PageBean<Admin> findUsersByPage(HashMap<String,Object> condition, int curPage, int pageSize);

    public int changeState(HashMap<String,Object> condition);

    public int resetPsd(HashMap<String,Object> condition);

    public int delUser(HashMap<String,Object> condition);

}
