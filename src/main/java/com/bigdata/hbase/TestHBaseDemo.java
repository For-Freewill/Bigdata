package com.bigdata.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;


public class TestHBaseDemo {
	public static void main(String[] args) throws Exception {
		//createTable();
		//insertOne();
		//get();
		//scan();
		//dropTable();
	}
	private  static void createTable() throws Exception{
		//指定ZooKeeper地址，从zk中获取HMaster的地址 
		//注意：ZK返回的是HMaster的主机名, 不是IP地址 ---> 配置Windows的hosts文件
		//C:\Windows\System32\drivers\etc\hosts
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "10.30.30.146");
		
		HBaseAdmin client = new HBaseAdmin(conf);
		
		HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("students"));
		
		HColumnDescriptor h1 = new HColumnDescriptor("info");
		HColumnDescriptor h2 = new HColumnDescriptor("grade");
		
		hTableDescriptor.addFamily(h1);
		hTableDescriptor.addFamily(h2);
		
		client.createTable(hTableDescriptor);
		client.close();
		}
	
	//插入单条数据
	private static void insertOne() throws Exception{
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "10.30.30.146");
		
		//指定表的客户端
		HTable table = new HTable(conf, "students");
		
		//构造一条数据
		Put put = new Put(Bytes.toBytes("stu001"));
		put.addColumn(Bytes.toBytes("info"),      //列族的名字
				      Bytes.toBytes("name"),   //列的名字
				      Bytes.toBytes("Tom2"));       //值
		
		//插入
		table.put(put);
		
		table.close();
	}
	
	//
	private static void get() throws Exception  {
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "10.30.30.146");
		
		//指定表的客户端
		HTable table = new HTable(conf, "students");
		
		Get get = new Get(Bytes.toBytes("stu001"));
		
		Result result = table.get(get);
		
		String name = Bytes.toString(result.getValue(Bytes.toBytes("info"),Bytes.toBytes("name")));
		
		System.out.println(name);
		table.close();
	}
	

	private static void scan()throws Exception  {
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "10.30.30.146");
		
		//指定表的客户端
		HTable table = new HTable(conf, "students");
		
		//创建一个扫描器 Scan
		Scan scanner = new Scan(); //----> 相当于: select * from students;
		//scanner.setFilter(filter)  ----> 过滤器
		
		//执行查询
		ResultScanner rs = table.getScanner(scanner); //返回ScannerResult ---> Oracle中的游标
		for(Result r:rs){
			String name = Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
			String age = Bytes.toString(r.getValue(Bytes.toBytes("info"), Bytes.toBytes("age")));
			System.out.println(name + "   "+ age);
		}
		table.close();
	}
	
	private static void dropTable() throws IOException {
		Configuration conf = new Configuration();
		conf.set("hbase.zookeeper.quorum", "10.30.30.146");
		
		//创建一个HBase的客户端
		HBaseAdmin client = new HBaseAdmin(conf);
		
		client.disableTable("students");
		client.deleteTable("students");
		
		client.close();
	}
}
