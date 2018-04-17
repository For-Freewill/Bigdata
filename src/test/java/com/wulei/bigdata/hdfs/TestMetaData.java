package com.wulei.bigdata.hdfs;

import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.junit.Test;

public class TestMetaData {

	@Test
	public void testCheckFileInfo() throws Exception{
			
		
		//����NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//�ͻ���
		FileSystem client = FileSystem.get(conf);
		//��ȡ��Ŀ¼�������ļ�����Ϣ
		FileStatus[] filesStatus = client.listStatus(new Path("/tools"));
		for(FileStatus f:filesStatus){
			System.out.println(f.isDirectory()?"Ŀ¼":"�ļ�");
			System.out.println(f.getPath().getName());
			System.out.println(f.getBlockSize());
			System.out.println("*************************");
		}
		
		client.close();
	}
	
	@Test
	public void testCheckFileBlock() throws Exception{
		//����NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");

		
		//�ͻ���
		FileSystem client = FileSystem.get(conf);
		
		//��ȡ���ļ�����Ϣ
		FileStatus fs = client.getFileStatus(new Path("/tools/a.zip"));
		
		//��ȡ�ļ������ݿ����Ϣ
		BlockLocation[] location = client.getFileBlockLocations(fs, 0, fs.getLen());
		for(BlockLocation block:location){
			//block.getHosts() ---> Ϊʲô����һ��String[]???
			System.out.println(Arrays.toString(block.getHosts()) + "\t"+ Arrays.toString(block.getNames()));
		}
		
		client.close();
	}
}
















