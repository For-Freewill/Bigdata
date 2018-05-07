package com.bigdata.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 工具类：
 * 1、获取链接 
 * 2、释放资源: Connection Statement ResultSet
 */
public class JDBCUtils {

	//Hive的驱动
	private static String driver = "org.apache.hive.jdbc.HiveDriver";
	//Oracle数据库: oracle.jdbc.OracleDriver
	
	//Hive的URL地址
	private static String url = "jdbc:hive2://10.30.30.146:10000/default";
	
	//注册数据库的驱动
	static{
		try{
			Class.forName(driver);
		}catch(Exception ex){
			throw new ExceptionInInitializerError(ex);
		}
	}

	//获取数据库Hive的链接
	public static Connection getConnection(){
		try{
			return DriverManager.getConnection(url);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
	}

	//释放资源
	//更深入一点的问题：Java GC 通过代码干预Java GC？
	public static void release(Connection conn,Statement st,ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				st = null;
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	}
}