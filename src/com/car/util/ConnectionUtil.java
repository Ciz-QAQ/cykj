package com.car.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionUtil {

	private static ConnectionUtil util;
	private Connection conn;
//	private Properties properties;
	private ComboPooledDataSource dataSource;

	//私有声明，载入配置文件
	private ConnectionUtil() {
//		properties = new Properties();
//		try {
//			properties.load(this.getClass().getClassLoader().getResourceAsStream("db.properties"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		dataSource=new ComboPooledDataSource("oracle");//调用默认配置&name
	}

	//连接数据库
	public Connection getConnection() {

		try {
			conn=dataSource.getConnection();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	return conn;

	}

	public void close(Connection conn){
		//关闭连接

			try {
				if (conn!=null){
				conn.close();
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

	}


	//单例返回连接
	public synchronized static ConnectionUtil getInstance() {
		if (util == null) {
			util = new ConnectionUtil();
		}
		return util;
	}

	public DataSource getDataSource(){
		return dataSource;
	}
}
