package com.bigdata.mapreduce.combiner;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class AvgSalaryMain {

	public static void main(String[] args) throws Exception {
		Job job = Job.getInstance(new Configuration());
		job.setJarByClass(AvgSalaryMain.class);  //main�������ڵ�class
		
		job.setMapperClass(AvgSalaryMapper.class);
		job.setMapOutputKeyClass(Text.class);    //k2������
		job.setMapOutputValueClass(DoubleWritable.class);  //v2������
		
		//设置combiner
		job.setCombinerClass(AvgSalaryReducer.class);
		
		job.setReducerClass(AvgSalaryReducer.class);
		job.setOutputKeyClass(Text.class);  //k4������
		job.setOutputValueClass(DoubleWritable.class);  //v4������
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);

	}

}
