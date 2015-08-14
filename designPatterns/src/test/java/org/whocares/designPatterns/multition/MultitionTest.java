package org.whocares.designPatterns.multition;

import org.junit.Test;

public class MultitionTest {

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			Multition instance = Multition.getInstance();
			System.out.println(instance);
		}
		System.out.println("============================");
		System.out.println(Multition.getInstance(Multition.FIRST_INSTANCE));
		System.out.println(Multition.getInstance(Multition.SECOND_INSTANCE));
	}

}
