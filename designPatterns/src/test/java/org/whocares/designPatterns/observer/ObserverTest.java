package org.whocares.designPatterns.observer;

import org.junit.Test;

public class ObserverTest {

	@Test
	public void test() {
		Subject subject = new Subject();
		subject.addObserver(new NameObserver());
		subject.addObserver(new TypeObserver());
		
		subject.setChanged();
		subject.setName("name123");
		subject.setChanged();
		subject.setType(1);
	}

}
