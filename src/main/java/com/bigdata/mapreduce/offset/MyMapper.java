package com.bigdata.mapreduce.offset;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, LongWritable, Text>{

	@Override
	protected void map(LongWritable k1, Text v1,Context context)
			throws IOException, InterruptedException {
		context.write(k1, v1);//<K1 V1>直接输出为<K2,V2>
	}

}
