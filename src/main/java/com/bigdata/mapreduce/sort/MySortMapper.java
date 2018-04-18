package com.bigdata.mapreduce.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MySortMapper extends Mapper<LongWritable, Text, IntWritable, NullWritable>{

	@Override
	protected void map(LongWritable k1, Text v1,Context context)
			throws IOException, InterruptedException {
		String data = v1.toString();//获取一列数据，7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
		String[] words = data.split(",");
		
		context.write(new IntWritable(Integer.parseInt(words[5])), NullWritable.get());

	}
	

}
