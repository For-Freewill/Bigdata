package com.bigdata.hdfs.directory;

import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.junit.Test;

public class TestMeta {

	@Test
	public void testCheckFileInfo() throws Exception{
			
		
		//配置NameNode地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://10.30.30.146:9000");
		
		//客户端
		FileSystem client = FileSystem.get(conf);
		//获取该目录下所有文件的信息
		FileStatus[] filesStatus = client.listStatus(new Path("/tools"));
		for(FileStatus f:filesStatus){
			System.out.println(f.isDirectory()?"目录":"文件");
			System.out.println(f.getPath().getName());
			System.out.println(f.getBlockSize());
			System.out.println(f.getAccessTime());
			System.out.println(f.getLen());
			System.out.println("*************************");
		}
		
		client.close();
	}
	
	@Test
	public void testCheckFileBlock() throws Exception{
		//配置NameNode地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://10.30.30.146:9000");

		
		//客户端
		FileSystem client = FileSystem.get(conf);
		
		//获取该文件的信息
		FileStatus fs = client.getFileStatus(new Path("/tools/a.zip"));
		
		//获取文件的数据块的信息
		BlockLocation[] location = client.getFileBlockLocations(fs, 0, fs.getLen());
		for(BlockLocation block:location){
			//block.getHosts() ---> 为什么返回一个String[]???
			System.out.println(Arrays.toString(block.getHosts()) + "\t"+ Arrays.toString(block.getNames()));
		}
		
		client.close();
	}
}
