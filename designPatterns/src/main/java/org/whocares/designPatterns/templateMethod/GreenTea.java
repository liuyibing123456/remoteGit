package org.whocares.designPatterns.templateMethod;

public class GreenTea extends Beverage {

	@Override
	protected void brew() {
		System.out.println("冲泡茶......");
	}

	@Override
	protected void addCondiments() {
	}

	@Override
	protected boolean addCondimentsHooks() {
		return false;
	}

}
