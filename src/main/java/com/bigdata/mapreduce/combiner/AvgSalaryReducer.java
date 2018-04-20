package com.bigdata.mapreduce.combiner;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

//                                                                k4    v4 
public class AvgSalaryReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	@Override
	protected void reduce(Text key3, Iterable<DoubleWritable> value3,Context context) throws IOException, InterruptedException {
		int count = 0;
		int total = 0;
		for(DoubleWritable sal:value3){
			total += sal.get();  
			count ++;          
		}
		
		context.write(new Text("Avarge Salary is :"), new DoubleWritable(total/count));
	}

}
