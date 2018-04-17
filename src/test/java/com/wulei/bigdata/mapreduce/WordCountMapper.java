package com.wulei.bigdata.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//                                      ����                 k1       v1    k2       v2
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		/*
		 * context ��ʾMapper��������
		 * ���ģ�HDFS
		 * ���ģ�Mapper
		 */
		//���ݣ� I love Beijing
		String data = value1.toString();
		
		//�ִ�
		String[] words = data.split(" ");
		
		//��� k2    v2
		for(String w:words){
			context.write(new Text(w), new IntWritable(1));
		}
	}

}















