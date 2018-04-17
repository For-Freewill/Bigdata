package com.wulei.bigdata.hdfs;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDataSourceMain {

	public static void main(String[] args) throws Exception {
		//�����ӳ��л�ȡ���� ʹ�����  �ͷ�����
		MyDataSourcePool pool = new MyDataSourcePool();
		
		for(int i=0;i<11;i++){
			Connection conn = pool.getConnection();//�õ������������������ݿ�����
			
			System.out.println("��"+i+"�����ӣ���"+ conn);
			
			conn.close(); //�����ݿ�����ӻ��������ݿ⣬���������ӳ�
		}

	}

}
