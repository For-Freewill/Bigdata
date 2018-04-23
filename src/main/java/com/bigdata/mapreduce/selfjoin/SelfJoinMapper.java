package com.bigdata.mapreduce.selfjoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SelfJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text> {

	@Override
	protected void map(LongWritable k1, Text v1, Context context)
			throws IOException, InterruptedException {
		String data = v1.toString();
		
		String[] words = data.split(",");
		//输出
		//1、作为老板表
		context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*" + words[1]));
		
		//2、作为员工表                                     输出老板号可能会产生Exception
		try{
			context.write(new IntWritable(Integer.parseInt(words[3])), new Text(words[1]));
		}catch(Exception ex){
			//如果产生异常，表示是大老板
			context.write(new IntWritable(-1), new Text(words[1]));
		}
	}

}
