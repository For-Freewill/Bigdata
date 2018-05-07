package com.bigdata.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

public class MyConcatString extends UDF{

	//必须重写一个方法，方法的名字必须叫：evaluate
	public String evaluate(String a,String b){
		return a+"*******"+b;
	}
}
