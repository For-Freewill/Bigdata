package com.bigdata.mapreduce.serializable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Dep implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Dep(String name, String age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Dep() {

	}
	public static void main(String[] args) throws IOException {
		Dep dep=new Dep();
		dep.setAge("18");
		dep.setName("wulei");
		
		ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream("d:\\dep.1"));
		obj.writeObject(dep);
		
		obj.close();
	}
	
}
