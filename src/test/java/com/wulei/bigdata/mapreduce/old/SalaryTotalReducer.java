package com.wulei.bigdata.mapreduce.old;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

//                                                k3      v3     k4      v4
public class SalaryTotalReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

	@Override
	protected void reduce(IntWritable k3, Iterable<IntWritable> v3,Context context)
			throws IOException, InterruptedException {
		//��v3��ͣ��õ��ò��ŵĹ����ܶ�
		int total = 0;
		for(IntWritable v:v3){
			total += v.get();
		}
		
		//���                    ���ź�   �ܶ�
		context.write(k3, new IntWritable(total));
	}

}
