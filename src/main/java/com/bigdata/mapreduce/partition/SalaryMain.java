package com.bigdata.mapreduce.partition;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SalaryMain {

	public static void main(String[] args) throws Exception {
		//  创建一个job
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(SalaryMain.class);
		
		//指定job的mapper和输出的类型   k2  v2
		job.setMapperClass(SalaryMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Employee.class);
		
		
		//设置分区
		job.setPartitionerClass(MyPartition.class);
		job.setNumReduceTasks(3);
		
		//指定job的reducer和输出的类型  k4   v4
		job.setReducerClass(SalaryReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Employee.class);
		
		//指定job的输入和输出的路径
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//执行任务
		job.waitForCompletion(true);
	}

}