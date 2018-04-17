package com.wulei.bigdata.hdfs;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTestMain {

	public static void main(String[] args) {
		//���������Ķ���
		MyBusiness obj = new MyBusinessImpl();
		obj.method1();
		obj.method2();
		
		//��дmethod1��ʵ�� ---> ���޸�Դ��
		//������������Ĵ������
		/*
		Proxy.newProxyInstance(loader, �������
				               interfaces, ��������ʵ�ֵĽӿ�
				               h ) InvocationHandler ��ʾ�ͻ�����ε��ô������
	 	*/
		
		MyBusiness proxyObj = (MyBusiness) Proxy.newProxyInstance(ProxyTestMain.class.getClassLoader(), 
				                                     obj.getClass().getInterfaces(), 
				                                     new InvocationHandler() {
														
										@Override
										public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
											// �ͻ��˵�һ�ε���
											/*
											 * method: �ͻ��˵��÷�����
											 * args  : �����Ĳ���
											 */
											if(method.getName().equals("method1")){
												//��д
												System.out.println("******��д��method1*********");
												return null;
											}else{
												//������Ȥ�ķ��� ֱ�ӵ��������Ķ������
												return method.invoke(obj, args);
											}
										}
					});
		
		//ͨ������������ method1  method2
		proxyObj.method1();
		proxyObj.method2();
	}

}












