package com.bigdata.mapreduce.partition;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;
//                                           K2    V2
public class MyPartition extends Partitioner<IntWritable, Employee>{

	@Override
	public int getPartition(IntWritable k2, Employee v2, int numPartitions) {
		if(v2.getDeptno() == 10){
			//放入1号分区中
			return 1%numPartitions;
		}else if(v2.getDeptno() == 20){
			//放入2号分区中
			return 2%numPartitions;
		}else{
			//放入0号分区中
			return 3%numPartitions;
		}
	}

}
