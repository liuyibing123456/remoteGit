package org.whocares.designPatterns.templateMethod;

public class BlackTea extends Beverage {

	@Override
	protected void brew() {
		System.out.println("冲泡茶......");
	}

	@Override
	protected void addCondiments() {
		System.out.println("加牛奶......");
	}

	@Override
	protected boolean addCondimentsHooks() {
		return true;
	}

}
