package org.whocares.designPatterns.strategy;

public class InsertSort implements Strategy {

	@Override
	public void operate() {
		System.out.println("直接插入排序");
	}

}
