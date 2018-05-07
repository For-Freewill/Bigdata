package com.bigdata.pig;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

//根据员工的薪水，判断薪水的级别
//调用  emp2 = foreach emp generate empno,ename,sal,运算函数(sal)
// emp2 = foreach emp generate empno,ename,sal,pig.CheckSalaryGrade(sal);
public class CheckSalaryGrade extends EvalFunc<String>{

	@Override
	public String exec(Tuple tuple) throws IOException {
		// 调用运行函数
		//tuple传递的参数值
		
		int sal = (int) tuple.get(0);
		if(sal <1000) return "Grade A";
		else if(sal>=1000 && sal<3000) return "Grade B";
		else return "Grade C";
	}
}
