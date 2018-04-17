package com.bigdata.hdfs.directory;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

/*
 * 原因：
 * Caused by: org.apache.hadoop.ipc.RemoteException(org.apache.hadoop.security.AccessControlException): 
 * Permission denied: user=lenovo, access=WRITE, inode="/folder1":root:supergroup:drwxr-xr-x
 * 
 * 当前用户：lenovo 执行w权限
 *  HDFS的根的权限：root:supergroup:drwxr-xr-x
 *  
 *  四种方式，执行程序：
 *  1、设置一个属性
 *  2、使用-D参数
 *  3、改变目录的权限  hdfs dfs -chmod 777 /folder2
 *  4、dfs.permissions  ---> false  禁用HDFS的权限检查功能
 */


public class MkDir {

	@Test
	public void test1() throws Exception{
		//方式一：设置一个属性，代表用户的身份
		System.setProperty("HADOOP_USER_NAME", "root");
		
		//指定NameNode的地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://10.30.30.146:9000");
		
		//获取一个HDFS的客户端
		FileSystem client = FileSystem.get(conf);
		//创建目录
		client.mkdirs(new Path("/folder1"));
				
		//关闭客户端
		client.close();
	}
	
	@Test
	public void test2() throws Exception{
		//指定NameNode的地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://10.30.30.146:9000");
		
		//获取一个HDFS的客户端
		FileSystem client = FileSystem.get(conf);
		//创建目录
		client.mkdirs(new Path("/folder2"));
				
		//关闭客户端
		client.close();
	}
	
	@Test
	public void test3() throws Exception{
		//指定NameNode的地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://10.30.30.146:9000");
		
		//获取一个HDFS的客户端
		FileSystem client = FileSystem.get(conf);
		//创建目录
		client.mkdirs(new Path("/folder2/folder3"));
				
		//关闭客户端
		client.close();
	}
	
	@Test
	public void test4() throws Exception{
		//指定NameNode的地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://10.30.30.146:9000");
		
		//获取一个HDFS的客户端
		FileSystem client = FileSystem.get(conf);
		//创建目录
		client.mkdirs(new Path("/folder4"));
				
		//关闭客户端
		client.close();
	}
}