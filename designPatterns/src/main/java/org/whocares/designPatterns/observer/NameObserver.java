package org.whocares.designPatterns.observer;

import java.util.Observable;
import java.util.Observer;

public class NameObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof String) {
			System.out.println("observable : " + o.getClass().getSimpleName()
					+ " , observer : " + this.getClass().getSimpleName()
					+ " , arg : " + arg);
		}
	}

}
