package com.bigdata.pig;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.pig.LoadFunc;
import org.apache.pig.backend.hadoop.executionengine.mapReduceLayer.PigSplit;
import org.apache.pig.data.BagFactory;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;

public class MyLoadFunction extends LoadFunc {

	//定义HDFS的输入流
	private RecordReader reader = null;
	
	@Override
	public InputFormat getInputFormat() throws IOException {
		// 输入数据的数类型是什么：字符串
		return new TextInputFormat();
	}

	@Override
	public Tuple getNext() throws IOException {
		// 对reader中读入的每一行数据进行处理
		//数据： I love Beijing
		//返回结果
		Tuple result = null;
		try{
			//判断是否有数据
			if(!this.reader.nextKeyValue()){
				//没有输入数据
				return result;
			}
			
			//读入了数据
			String data = this.reader.getCurrentValue().toString();
			//分词操作
			String[] words = data.split(" ");
			
			//生成返回的tuple
			result = TupleFactory.getInstance().newTuple();
			
			//把每个单词单独生成一个tuple，然后把这些tuple放入bag中，再把这个bag放入result中
			//创建一个表
			DataBag bag = BagFactory.getInstance().newDefaultBag();
			for(String w:words){
				//为每个单词生成一个新的tuple
				Tuple aTuple  = TupleFactory.getInstance().newTuple();
				aTuple.append(w);  //将单词放入tuple
				
				//再把这个tuple放入bag
				bag.add(aTuple);
			}
			
			//再把这个bag放入result中
			result.append(bag);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return result;
	}

	@Override
	public void prepareToRead(RecordReader reader, PigSplit arg1) throws IOException {
		//reader代表HDFS的输入流
		this.reader = reader;
	}

	@Override
	public void setLocation(String path, Job job) throws IOException {
		// 指定HDFS的路径
		FileInputFormat.setInputPaths(job, new Path(path));
	}
}
