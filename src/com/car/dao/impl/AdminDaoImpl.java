package com.car.dao.impl;


import com.car.bean.Admin;
import com.car.dao.AdminDao;
import com.car.util.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    private Admin admin;
    private Connection connection;
    private PreparedStatement pps;

    public AdminDaoImpl(){
        admin=null;
        connection=null;
        pps=null;
    }

    @Override
    public Admin login(String account, String psd) {
        System.out.println("AdminDaoImpl login ing...");

        connection=null;
        Admin admin=null;

        try {
            connection = ConnectionUtil.getInstance().getConnection();
            String sql = "select * from adminInfo where adminID=? and adminPsd=?";

            QueryRunner qr = new QueryRunner();
            Object[] params = {account,psd};
            admin = qr.query(connection,sql,new BeanHandler<>(Admin.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.getInstance().close(connection);
        }

        System.out.println("sql :"+admin);

        return admin;
    }

    @Override
    public int findCount(HashMap<String, Object> condition) {
        System.out.println("findCount ing...");

        connection= ConnectionUtil.getInstance().getConnection();
        String sql="";
        BigDecimal count= BigDecimal.valueOf(0);

        sql="select count(*) from consultant where 1=1";

        if (condition.size()>0){
            if (condition.containsKey("uname")){
                sql+=" and cAccount like '%"+condition.get("uname")+"%'";
            }

            if (condition.containsKey("ustate")){
                System.out.println("state:"+condition.get("ustate"));

                String state= (String) condition.get("ustate");
                if (state.equals("禁用")){
                    state="2";
                }else if(state.equals("启用")){
                    state="1";
                }

                sql+=" and State ='"+state+"'";
            }

            if (condition.containsKey("jobTitle")){
                sql+=" and jobTitle like '%"+condition.get("jobTitle")+"%'";
            }
        }

        try {
            pps=connection.prepareStatement(sql);

            ResultSet res=pps.executeQuery();

            if (res.next()){
                count=res.getBigDecimal(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionUtil.getInstance().close(connection);
        }
        System.out.println("count: "+count);

        return count.intValue();
    }

    @Override
    public List<Admin> findUserByPageNum(HashMap<String, Object> condition, int page) {
        System.out.println("AdminDaoImpl findUserByPageNum ing...");

        List<Admin> admins=new ArrayList<>();

        try {
            connection=null;
            connection= ConnectionUtil.getInstance().getConnection();
            String sql="";
            int pageSize=5;

            sql="select cID,cACCOUNT,cPsd,cMONEY,STATE,cName,cGoodAt,cIntroduce,cBackGround,cEducation,pid,jobTitle from " +
                    "(select u.* ,ROWNUM rn from consultant u where 1=1" ;

            if (condition.size()>0){
                if (condition.containsKey("uname")){
                    sql+=" and cAccount like '%"+condition.get("uname")+"%'";
                }

                if (condition.containsKey("ustate")){
                    System.out.println("state:"+condition.get("ustate"));

                    String state= (String) condition.get("ustate");
                    if (state.equals("禁用")){
                        state="2";
                    }else if(state.equals("启用")){
                        state="1";
                    }

                    sql+=" and State ='"+state+"'";
                }

                if (condition.containsKey("jobTitle")){
                    sql+=" and jobTitle = '%"+condition.get("jobTitle")+"%'";
                }
            }

            sql+=")t where t.rn between ? and ?";


            System.out.println(sql);
            QueryRunner queryRunner=new QueryRunner();

            Object[] params={(page-1)*pageSize+1,page*pageSize};

            admins=queryRunner.query(connection,sql,new BeanListHandler<>(Admin.class),params);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionUtil.getInstance().close(connection);
        }

        System.out.println("List size: "+admins.size());
        return admins;
    }

    @Override
    public int changeState(HashMap<String, Object> condition) {
        System.out.println("AdminDaoImpl changeState ing...");

        Connection connection = ConnectionUtil.getInstance().getConnection();
        String sql = "update consultant set State = ? where cAccount = ? ";
        QueryRunner qr = new QueryRunner();

        String uState = (String) condition.get("state");
        String userId = (String) condition.get("userAccount");

        String state="";
        if (uState.equals("禁用")){
            state="1";
        }else if(uState.equals("启用")){
            state="2";
        }

        Object[] parames = {state,userId};
        int i=0;
        try {
            i = qr.update(connection,sql,parames);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.getInstance().close(connection);
        }
        System.out.println("result:"+i);
        return i;
    }

    @Override
    public int resetPsd(HashMap<String, Object> condition) {
        System.out.println("AdminDaoImpl resetPsd ing...");

        Connection connection = ConnectionUtil.getInstance().getConnection();
        String sql = "update consultant set cPsd = 'e10adc3949ba59abbe56e057f20f883e' where cAccount = ? ";
        QueryRunner qr = new QueryRunner();

        String userAccount = (String) condition.get("userAccount");

        Object[] parames = {userAccount};
        int j=0;
        try {
            j = qr.update(connection,sql,parames);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.getInstance().close(connection);
        }
        System.out.println("result:"+j);
        return j;
    }

    @Override
    public int delUser(HashMap<String, Object> condition) {
        System.out.println("AdminDaoImpl resetPsd ing...");

        Connection connection = ConnectionUtil.getInstance().getConnection();
        String sql = "DELETE FROM consultant WHERE cAccount=?";
        QueryRunner qr = new QueryRunner();

        String userAccount = (String) condition.get("userAccount");

        Object[] parames = {userAccount};

        int del=-1;
        try {
            del = qr.update(connection,sql,parames);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.getInstance().close(connection);
        }
        System.out.println("result:"+del);

        return del;
    }

}
