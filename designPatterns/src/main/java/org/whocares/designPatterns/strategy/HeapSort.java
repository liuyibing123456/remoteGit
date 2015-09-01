package org.whocares.designPatterns.strategy;

public class HeapSort implements Strategy {

	@Override
	public void operate() {
		System.out.println("堆排序");
	}

}
