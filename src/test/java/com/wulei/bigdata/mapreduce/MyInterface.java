package com.wulei.bigdata.mapreduce;

import org.apache.hadoop.ipc.VersionedProtocol;

public interface MyInterface extends VersionedProtocol {

	//����һ���汾��
	public static long versionID=1;
	
	//����ͻ��˿��Ե��õķ���
	public String sayHello(String name);
}
