package com.bigdata.mapreduce.selfjoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SelfJoinReducer extends Reducer<IntWritable, Text, Text, Text> {

	@Override
	protected void reduce(IntWritable k3, Iterable<Text> v3,Context context)
			throws IOException, InterruptedException {
		//定义变量来保存老板的姓名、员工的姓名
		String bossName = "";
		String empNameList = "";
		
		for(Text t:v3){
			String name = t.toString();
			
			//判断是否存在*
			int index = name.indexOf("*");
			if(index >= 0){
				//表示老板名字
				bossName = name.substring(1);
			}else{
				//表示员工的姓名
				empNameList = name+";"+empNameList;
			}
		}
		
		//输出
		//判断：有老板、有员工的时候  才输出
		if(bossName.length() >0 && empNameList.length() > 0)
			context.write(new Text(bossName), new Text(empNameList));
		

	}

}
