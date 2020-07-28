package com.car.dao.impl;

import com.car.bean.CarInfo;
import com.car.dao.CarInfoDao;
import com.car.util.ConnectionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarInfoDaoImpl implements CarInfoDao {

    private CarInfo carInfo;
    private Connection connection;
    private PreparedStatement pps;

    public CarInfoDaoImpl(){
        carInfo=null;
        connection=null;
        pps=null;
    }

    @Override
    public int findCount(HashMap<String, Object> condition) {
        System.out.println("findCount ing...");
        //get pageInfo

        connection= ConnectionUtil.getInstance().getConnection();
        String sql="";
        BigDecimal count= BigDecimal.valueOf(0);

        sql="select count(*) from CarInfo where 1=1";

        if (condition.size()>0){

            if (condition.containsKey("cNumber")){
                System.out.println("cNumber:"+condition.get("cNumber"));

                sql+=" and CNumber='"+condition.get("cNumber")+"'";
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
    public List<CarInfo> getCarInfo(HashMap<String, Object> condition, int page) {
        System.out.println("AdminDaoImpl findUserByPageNum ing...");
        //get carInfo list

        page=5;

        List<CarInfo> carInfos=new ArrayList<>();

        try {
            connection=null;
            connection= ConnectionUtil.getInstance().getConnection();
            String sql="";
            int pageSize=5;

            sql="select * from (select c.*,ROWNUM rn from CARINFO c) t  where 1=1" ;

            if (condition.size()>0){

                if (condition.containsKey("cNumber")){
                    System.out.println("DaoImpl cNumber:"+condition.get("cNumber"));

                    sql+=" and CNumber like '%"+condition.get("cNumber")+"%'";
                }

            }

            sql+=" and t.rn between ? and ?";

            QueryRunner queryRunner=new QueryRunner();

            Object[] params={(page-1)*pageSize+1,page*pageSize};

            System.out.println("sql:"+sql);

            carInfos=queryRunner.query(connection,sql,new BeanListHandler<>(CarInfo.class),params);
            System.out.println(carInfos);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionUtil.getInstance().close(connection);
        }

        System.out.println("List size: "+carInfos.size());
        return carInfos;
    }
}
