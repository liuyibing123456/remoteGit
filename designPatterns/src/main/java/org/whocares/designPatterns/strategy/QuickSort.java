package org.whocares.designPatterns.strategy;

public class QuickSort implements Strategy {

	@Override
	public void operate() {
		System.out.println("快速排序");
	}

}
