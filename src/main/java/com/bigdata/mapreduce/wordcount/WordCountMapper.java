package com.bigdata.mapreduce.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//泛型                                                                                                    k1            v1    k2       v2
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		/*
		 * context 表示Mapper的上下文
		 * 上文：HDFS
		 * 下文：Mapper
		 */
		//数据： I love Beijing,K1是偏移量，可以忽略处理，这里处理V1，V1是每一行的字符串，因此单行字符串字字节
		//作为tostring
		String data = value1.toString();
		
		//<K2,V2>=<每个单词，1次>，对每一行的一个单词出现了就计数1
		String[] words = data.split(" ");
		for (String string : words) {
			context.write(new Text(string), new IntWritable(1));
		}
	}

}
