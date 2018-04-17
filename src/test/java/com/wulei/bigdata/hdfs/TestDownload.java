package com.wulei.bigdata.hdfs;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class TestDownload {

	@Test
	public void test1() throws Exception{
		//����NameNode��ַ
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://192.168.157.111:9000");
		
		//�ͻ���
		FileSystem client = FileSystem.get(conf);
		
		//��һ�������� <------HDFS
		InputStream in = client.open(new Path("/tools/a.zip"));
		
		//����һ�������  ----> d:\temp\aa.zip
		OutputStream out = new FileOutputStream("d:\\temp\\bb.zip");
		
		//ʹ�ù�����򻯳���
		IOUtils.copyBytes(in, out, 1024);
		
//		//����һ��������
//		byte[] buffer = new byte[1024];
//		int len=0;
//		while((len=in.read(buffer)) >0) {
//			//��ȡ��������
//			out.write(buffer, 0, len);
//		}
//		
//		out.flush();
//		
//		out.close();
//		in.close();
	}
}


















