package com.wulei.bigdata.hdfs;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class TestUpload {

	@Test
	public void test1() throws Exception{
		//����һ��������
		InputStream in = new FileInputStream("d:\\dowload\\hadoop-2.4.1.zip");
		
		
		//����NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//�ͻ���
		FileSystem client = FileSystem.get(conf);
		
		//�õ�һ�������
		OutputStream out = client.create(new Path("/tools/a.zip"));
		
		//����һ��������
		byte[] buffer = new byte[1024];
		int len=0;
		while((len=in.read(buffer)) >0) {
			//��ȡ��������
			out.write(buffer, 0, len);
		}
		
		out.flush();
		
		out.close();
		in.close();
	}
	
	@Test
	public void test2() throws Exception{
		//����һ��������
		InputStream in = new FileInputStream("d:\\dowload\\hadoop-2.4.1.zip");
		
		//����NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//�ͻ���
		FileSystem client = FileSystem.get(conf);
		
		//�õ�һ�������
		OutputStream out = client.create(new Path("/tools/b.zip"));		
		
		//ʹ�ù�����򻯳���
		IOUtils.copyBytes(in, out, 1024);
	}
}


















