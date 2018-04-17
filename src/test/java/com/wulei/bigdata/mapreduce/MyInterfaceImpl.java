package com.wulei.bigdata.mapreduce;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;

public class MyInterfaceImpl implements MyInterface {

	@Override
	public ProtocolSignature getProtocolSignature(String arg0, long arg1, int arg2) throws IOException {
		// ָ��ǩ�����汾�ţ�
		return new ProtocolSignature(MyInterface.versionID, null);
	}

	@Override
	public long getProtocolVersion(String arg0, long arg1) throws IOException {
		// ���صĸ�ʵ����İ汾��
		return MyInterface.versionID;
	}

	@Override
	public String sayHello(String name) {
		System.out.println("********* ���õ���Server��*********");
		return "Hello " + name;
	}

}
