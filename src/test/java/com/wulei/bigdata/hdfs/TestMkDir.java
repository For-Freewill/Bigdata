package com.wulei.bigdata.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

/*
 * ԭ��
 * Caused by: org.apache.hadoop.ipc.RemoteException(org.apache.hadoop.security.AccessControlException): 
 * Permission denied: user=lenovo, access=WRITE, inode="/folder1":root:supergroup:drwxr-xr-x
 * 
 * ��ǰ�û���lenovo ִ��wȨ��
 *  HDFS�ĸ���Ȩ�ޣ�root:supergroup:drwxr-xr-x
 *  
 *  ���ַ�ʽ��ִ�г���
 *  1������һ������
 *  2��ʹ��-D����
 *  3���ı�Ŀ¼��Ȩ��  hdfs dfs -chmod 777 /folder2
 *  4��dfs.permissions  ---> false  ����HDFS��Ȩ�޼�鹦��
 */


public class TestMkDir {

	@Test
	public void test1() throws Exception{
		//��ʽһ������һ�����ԣ������û������
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//ָ��NameNode�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//��ȡһ��HDFS�Ŀͻ���
		FileSystem client = FileSystem.get(conf);
		//����Ŀ¼
		client.mkdirs(new Path("/folder1"));
				
		//�رտͻ���
		client.close();
	}
	
	@Test
	public void test2() throws Exception{
		//ָ��NameNode�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//��ȡһ��HDFS�Ŀͻ���
		FileSystem client = FileSystem.get(conf);
		//����Ŀ¼
		client.mkdirs(new Path("/folder2"));
				
		//�رտͻ���
		client.close();
	}
	
	@Test
	public void test3() throws Exception{
		//ָ��NameNode�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//��ȡһ��HDFS�Ŀͻ���
		FileSystem client = FileSystem.get(conf);
		//����Ŀ¼
		client.mkdirs(new Path("/folder2/folder3"));
				
		//�رտͻ���
		client.close();
	}
	
	@Test
	public void test4() throws Exception{
		//ָ��NameNode�ĵ�ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//��ȡһ��HDFS�Ŀͻ���
		FileSystem client = FileSystem.get(conf);
		//����Ŀ¼
		client.mkdirs(new Path("/folder4"));
				
		//�رտͻ���
		client.close();
	}
}
































