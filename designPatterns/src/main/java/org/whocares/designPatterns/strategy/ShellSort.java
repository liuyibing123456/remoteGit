package org.whocares.designPatterns.strategy;

public class ShellSort implements Strategy {

	@Override
	public void operate() {
		System.out.println("希尔排序");
	}

}
