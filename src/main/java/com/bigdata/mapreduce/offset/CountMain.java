package com.bigdata.mapreduce.offset;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

public class CountMain {
	public static void main(String[] args) throws Exception {
		// 创建一个job和任务入口
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(CountMain.class);  //main方法所在的class
		
		//指定job的mapper和输出的类型<k2 v2>
		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(LongWritable.class);    //k2的类型
		job.setMapOutputValueClass(Text.class);  //v2的类型
		
		//指定job的输入和输出
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//执行job
		job.waitForCompletion(true);
		
//		最终的输出：偏移量指的是每一行开始的字节数
//		0	Traceback (most recent call last):
//		35	  File "/usr/local/lib/python2.7/site-packages/pip/_internal/basecommand.py", line 228, in main
//		131	    status = self.run(options, args)

	}

}
