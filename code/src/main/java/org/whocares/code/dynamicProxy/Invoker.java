package org.whocares.code.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Invoker implements InvocationHandler{

	private Object obj;
	
	public Invoker(Object obj) {
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		method.invoke(this.obj, args);
		return null;
	}

}
