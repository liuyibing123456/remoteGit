package org.whocares.designPatterns.singleton;

import org.junit.Assert;
import org.junit.Test;

public class SingletonTest {

	@Test
	public void testSingleton() {
		SingletonOfHungry instance1 = SingletonOfHungry.getInstance();
		SingletonOfHungry instance2 = SingletonOfHungry.getInstance();
		Assert.assertEquals(instance1, instance2);
	}
}
