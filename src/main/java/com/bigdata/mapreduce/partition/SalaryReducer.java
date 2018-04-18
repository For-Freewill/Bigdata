package com.bigdata.mapreduce.partition;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

//                                                k3            v3     k4      v4
public class SalaryReducer extends Reducer<IntWritable, Employee, IntWritable, Employee> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Employee> v3,Context context)
			throws IOException, InterruptedException {

		for(Employee e:v3){
			context.write(k3, e);
		}
	}

}
