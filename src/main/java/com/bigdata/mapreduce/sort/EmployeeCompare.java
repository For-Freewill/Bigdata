package com.bigdata.mapreduce.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

//数据: 7654,MARTIN,SALESMAN,7698,1981/9/28,1250,1400,30
public class EmployeeCompare implements WritableComparable<EmployeeCompare>{
	
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	

	@Override
	public int compareTo(EmployeeCompare o) {
		// 多个列的排序：select * from emp order by deptno,sal;
		//首先、按照deptno排序
		if(this.deptno > o.getDeptno()){
			return 1;
		}else if(this.deptno < o.getDeptno()){
			return -1;
		}else if (this.sal >= o.getSal()) {
			return 1;
		}else {
			return -1;
		}
//		
//		//如果deptno相等，按照sal排序
//		if(this.sal >= o.getSal()){
//			return 1;
//		}else{
//			return -1;
//		}
	}
	
	@Override
	public String toString() {
		return "Employee [empno=" + empno + ", ename=" + ename + ", sal=" + sal + ", deptno=" + deptno + "]";
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		// 反序列化
		this.empno = input.readInt();
		this.ename = input.readUTF();
		this.job = input.readUTF();
		this.mgr = input.readInt();
		this.hiredate = input.readUTF();
		this.sal = input.readInt();
		this.comm = input.readInt();
		this.deptno = input.readInt();
	}
	
	@Override
	public void write(DataOutput output) throws IOException {
		// 序列化
		output.writeInt(this.empno);
		output.writeUTF(this.ename);
		output.writeUTF(this.job);
		output.writeInt(this.mgr);
		output.writeUTF(this.hiredate);
		output.writeInt(this.sal);
		output.writeInt(this.comm);
		output.writeInt(this.deptno);
	}	
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

}
