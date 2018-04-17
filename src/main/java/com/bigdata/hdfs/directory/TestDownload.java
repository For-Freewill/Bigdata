package com.bigdata.hdfs.directory;

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
		//配置NameNode地址
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://10.30.30.146:9000");
		
		//客户端
		FileSystem client = FileSystem.get(conf);
		
		//打开一个输入流 <------HDFS
		InputStream in = client.open(new Path("/tools/a.zip"));
		
		//构造一个输出流  ----> d:\temp\aa.zip
		OutputStream out = new FileOutputStream("d:\\bb.zip");
		
		//使用工具类简化程序
		IOUtils.copyBytes(in, out, 1024);
		
//		//构造一个缓冲区
//		byte[] buffer = new byte[1024];
//		int len=0;
//		while((len=in.read(buffer)) >0) {
//			//读取到了数据
//			out.write(buffer, 0, len);
//		}
//		
//		out.flush();
//		
//		out.close();
//		in.close();
	}
}
