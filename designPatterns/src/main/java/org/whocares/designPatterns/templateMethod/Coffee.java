package org.whocares.designPatterns.templateMethod;

public class Coffee extends Beverage {

	@Override
	protected void brew() {
		System.out.println("冲泡咖啡......");
	}

	@Override
	protected void addCondiments() {
		System.out.println("加入牛奶和糖......");
	}

	@Override
	protected boolean addCondimentsHooks() {
		return true;
	}

}
