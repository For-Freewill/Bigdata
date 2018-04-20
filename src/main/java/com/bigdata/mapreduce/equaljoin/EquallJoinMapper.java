package com.bigdata.mapreduce.equaljoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EquallJoinMapper extends Mapper<LongWritable, Text, IntWritable, Text>{

	@Override
	protected void map(LongWritable k1, Text v1, Context context)
			throws IOException, InterruptedException {
		String data = v1.toString();
		
		//分词
		String[] words = data.split(",");
		
		if (words.length==8) {
			context.write(new IntWritable(Integer.parseInt(words[7])), new Text(words[1]));
		} else {
			context.write(new IntWritable(Integer.parseInt(words[0])), new Text("*"+words[1]));
		}
	}
	
}
