package org.whocares.code.dynamicProxy;

public class SubjectImpl implements Subject {

	@Override
	public void doSomething() {
		System.out.println(this.getClass().getName() + " doSomething...");

	}

}
