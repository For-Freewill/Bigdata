package com.wulei.bigdata.mapreduce;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;


public class RPCClient {

	public static void main(String[] args) throws Exception {
		//�õ����Ƿ������˵�һ���������
		MyInterface proxy = RPC.getProxy(MyInterface.class,  //���÷������˵Ľӿ�
										 MyInterface.versionID,      // �汾��
									     new InetSocketAddress("localhost", 7788), //ָ��RPC Server�ĵ�ַ
									     new Configuration());

		String result = proxy.sayHello("Tom");
		System.out.println("����ǣ�"+ result);
	}

}
