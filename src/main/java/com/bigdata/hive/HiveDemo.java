package com.bigdata.hive;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveDemo {

	public static void main(String[] args) {
		//查询员工信息
		String sql = "select * from emp1";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			conn = JDBCUtils.getConnection();
			
			//得到SQL的运行环境
			st = conn.createStatement();
			
			//运行SQL
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				//姓名和薪水
				//注意：好像不能通过列的索引号获取
				
				String ename = rs.getString("ename");
				double sal = rs.getDouble("sal");
				System.out.println(ename+"\t"+sal);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			JDBCUtils.release(conn, st, rs);
		}

	}

}
