package com.bigdata.pig;

import java.io.IOException;

import org.apache.pig.FilterFunc;
import org.apache.pig.data.Tuple;

//查询薪水大于2000的员工
//调用 emp3 = filter emp by 过滤函数(sal)
//  emp3 = filter emp by pig.IsSalaryTooHigh(sal);
public class IsSalaryTooHigh extends FilterFunc {

	@Override
	public Boolean exec(Tuple tuple) throws IOException {
		//取出薪水
		int sal = (int) tuple.get(0);
		return sal>2000?true:false;
	}
}
