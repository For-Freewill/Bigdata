package com.bigdata.memcached;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.junit.Test;

import net.spy.memcached.MemcachedClient;

public class TestMemcached {

	@Test
	public void testInsert() throws Exception{
		//创建一个MemCached客户端
		MemcachedClient client = new MemcachedClient(new InetSocketAddress("10.30.30.146", 11211));
		
		//插入数据
		Future<Boolean> result =  client.set("key2", 0, "HelloWorld");
		if(result.get().booleanValue()){
			//插入成功
			client.shutdown();
		}
	}
	
	@Test
	public void testGet() throws Exception{
		//创建一个MemCached客户端
		MemcachedClient client = new MemcachedClient(new InetSocketAddress("10.30.30.146", 11211));	
		
		Object value = client.get("key2");
		System.out.println(value);
		
		client.shutdown();
	}
	
	@Test 
	public void testInsertObject(){
		try{
			//创建一个MemCached客户端
			MemcachedClient client = new MemcachedClient(new InetSocketAddress("10.30.30.146", 11211));	
			
			//创建一个学生对象
			Student s = new Student();
			
			//插入数据
			Future<Boolean> result =  client.set("s01", 0, s);
			if(result.get().booleanValue()){
				//插入成功
				client.shutdown();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testInsert2() throws Exception{
		//通过一个List指定三个MemCached的实例
		List<InetSocketAddress> list = new ArrayList<>();
		list.add(new InetSocketAddress("10.30.30.146", 11211));
		list.add(new InetSocketAddress("10.30.30.146", 11212));
		list.add(new InetSocketAddress("10.30.30.146", 11213));
		
		//创建一个MemCached客户端
		MemcachedClient client = new MemcachedClient(list);
		
		//插入20条数据
		for(int i=0;i<20;i++){
			System.out.println("插入的数据是:" + i);
			
			client.set("key"+i, 0, "value:" + i);
			
			//让当前线程睡2000秒
			Thread.sleep(2000);
		}
		
		client.shutdown();
		System.out.println("完成");
	}
}

//学生类
class Student implements Serializable{
	
}