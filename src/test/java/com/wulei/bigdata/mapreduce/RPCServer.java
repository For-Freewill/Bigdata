package com.wulei.bigdata.mapreduce;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class RPCServer {

	public static void main(String[] args) throws Exception {
		//����һ��RPC Builder
		RPC.Builder builder = new RPC.Builder(new Configuration());
		
		//ָ��RPC Server�Ĳ���
		builder.setBindAddress("localhost");
		builder.setPort(7788);
		
		//���Լ��ĳ�����Server��
		builder.setProtocol(MyInterface.class);
		builder.setInstance(new MyInterfaceImpl());
		
		//����Server
		Server server = builder.build();
		
		//����
		server.start();

	}

}














