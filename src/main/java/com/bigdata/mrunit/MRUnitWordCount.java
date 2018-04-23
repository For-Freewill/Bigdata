package com.bigdata.mrunit;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Test;

public class MRUnitWordCount {

	@Test
	public void testMapper() throws Exception{
		//设置一个环境变量
		//System.setProperty("hadoop.home.dir", "D:\\download\\hadoop-2.4.1\\hadoop-2.4.1");
		
		//创建一个测试对象
		WordCountMapper mapper = new WordCountMapper();
		
		//创建一个MapDriver，进行单元测试
		MapDriver<LongWritable, Text, Text, IntWritable> driver = new MapDriver<>(mapper);
		
		//指定map的输入： k1  v1
		driver.withInput(new LongWritable(1), new Text("I love Beijing"));
		
		//指定Map的输出: k2   v2  -----> 是我们期望得到的结果
		driver.withOutput(new Text("I"), new IntWritable(1))
			  .withOutput(new Text("love"), new IntWritable(1))
			  .withOutput(new Text("Beijing"), new IntWritable(1));
		
		//执行单元测试：对比：期望的结果和实际运行的结果
		driver.runTest();
	}
	
	@Test
	public void testReducer() throws Exception{
		//设置一个环境变量
		//System.setProperty("hadoop.home.dir", "D:\\download\\hadoop-2.4.1\\hadoop-2.4.1");
		
		//创建一个测试对象
		WordCountReducer reducer = new WordCountReducer();
		
		//创建一个ReducerDriver，进行单元测试
		ReduceDriver<Text, IntWritable, Text, IntWritable> driver = new ReduceDriver<>(reducer);
		
		//构造一下value3
		List<IntWritable> value3 = new ArrayList<>();
		value3.add(new IntWritable(1));
		value3.add(new IntWritable(1));
		value3.add(new IntWritable(1));
		
		//指定reducer的输入
		driver.withInput(new Text("Beijing"), value3);
		
		//指定reducer的输出: 期望得到的结果
		driver.withOutput(new Text("Beijing"), new IntWritable(3));
		
		//执行单元测试
		driver.runTest();
	}
	
	@Test
	public void testJob() throws Exception{
		//设置一个环境变量
		//System.setProperty("hadoop.home.dir", "D:\\download\\hadoop-2.4.1\\hadoop-2.4.1");
		
		//创建一个测试对象
		WordCountMapper mapper = new WordCountMapper();
		WordCountReducer reducer = new WordCountReducer();	
		
		//创建一个MapReduceDriver，进行单元测试
		MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>
			driver = new MapReduceDriver<>(mapper,reducer);
		
		//指定map的输入
		driver.withInput(new LongWritable(1), new Text("I love Beijing"))
			  .withInput(new LongWritable(1), new Text("I love China"))
			  .withInput(new LongWritable(1), new Text("Beijing is the capital of China"));
		
		//指定reducer的输出  ---> 期望得到结果
		driver.withOutput(new Text("Beijing"), new IntWritable(2))
			  .withOutput(new Text("China"), new IntWritable(2))
			  .withOutput(new Text("I"), new IntWritable(2))
			  .withOutput(new Text("capital"), new IntWritable(1))
			  .withOutput(new Text("is"), new IntWritable(1))
			  .withOutput(new Text("love"), new IntWritable(2))
			  .withOutput(new Text("of"), new IntWritable(1))
			  .withOutput(new Text("the"), new IntWritable(1));
		
		//作业：把上面的程序改对了
		
		driver.runTest();
	}
	
//	Beijing	2
//	China	2
//	I	2
//	capital	1
//	is	1
//	love	2
//	of	1
//	the	1

}