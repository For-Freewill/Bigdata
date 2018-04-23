package com.bigdata.mapreduce.revertedindex;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class RevertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

	@Override
	protected void map(LongWritable key1, Text value1, Context context)
			throws IOException, InterruptedException {
		// 数据：I love Beijing and love Shanghai
		//得到数据来自哪个文件？？？  /mydata/data01.txt
		String path = ((FileSplit)context.getInputSplit()).getPath().toString();
		
		//得到文件名
		//最后一个斜线
		int index = path.lastIndexOf("/");
		//文件名
		String fileName = path.substring(index+1);
		
		//分词
		String[] words = value1.toString().split(" ");
		for(String w:words){
			//格式：  单词:文件名
			context.write(new Text(w+":"+fileName), new Text("1"));
		}
	}
}

