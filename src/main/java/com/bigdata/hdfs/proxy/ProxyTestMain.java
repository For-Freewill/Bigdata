package com.bigdata.hdfs.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTestMain {
	public static void main(String[] args) {
		MyBusiness obj = new MyBusinessImpl();
		
		MyBusiness objProxy = (MyBusiness) Proxy.newProxyInstance(ProxyTestMain.class.getClassLoader(),
													obj.getClass().getInterfaces(),													new InvocationHandler() {
														
														@Override
														public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
															if (method.getName().equals("method1")) {
																System.out.println("重写的method1");
																return null;
															} else {
																return method.invoke(obj, args);
															}
														}
													});
		objProxy.method1();
		objProxy.method2();
	}

}
