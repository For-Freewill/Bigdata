package com.bigdata.mapreduce.equaljoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EqualJoinReducer extends Reducer<IntWritable, Text, Text, Text> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3, Context context)
			throws IOException, InterruptedException {
		//从value3中解析出 部门名称 和员工姓名
		String dname = "";  //部门名称
		String empListName = "";//所有员工的姓名
		
		for (Text text : v3) {
			String name = text.toString();
			
			//判断是否存储*号
			int index = name.indexOf("*");
			if(index >=0){
				//是部门名称，去掉第一个*号
				dname = name.substring(1);
			}else{
				//是员工的姓名
				empListName = name+";"+empListName;
			}
			
		}
		context.write(new Text(dname), new Text(empListName));
	}
	

}
