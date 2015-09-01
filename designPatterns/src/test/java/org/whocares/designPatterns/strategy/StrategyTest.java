package org.whocares.designPatterns.strategy;

import org.junit.Test;

public class StrategyTest {

	@Test
	public void test() {
		Context context = new Context(new ShellSort());
		context.operate();
		context = new Context(new QuickSort());
		context.operate();
	}

}
