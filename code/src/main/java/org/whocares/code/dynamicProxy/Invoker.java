package org.whocares.code.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Invoker implements InvocationHandler {

	/*
	 * 被代理对象
	 */
	private Object target;

	public Invoker(Object target) {
		this.target = target;
	}

	/**
	 * 代理对象调用该方法
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("before......");
		method.invoke(this.target, args);
		System.out.println("after......");
		return null;
	}

	/**
	 * 
	 * @return 代理对象
	 */
	public Object bindProxy() {
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
				this.target.getClass().getInterfaces(), this);
	}

}
