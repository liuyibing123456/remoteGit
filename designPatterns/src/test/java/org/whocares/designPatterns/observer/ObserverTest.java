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
	
	@Test
	public void testMultiThread() {
		final Subject subject = new Subject();
		
		for(int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					subject.addObserver(new NameObserver());
					subject.addObserver(new TypeObserver());
				}
			}).start();
		}
	}

}
