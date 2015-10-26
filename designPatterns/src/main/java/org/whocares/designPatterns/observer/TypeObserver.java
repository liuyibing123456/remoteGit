package org.whocares.designPatterns.observer;

import java.util.Observable;
import java.util.Observer;

public class TypeObserver implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Integer) {
			System.out.println("observable : " + o.getClass().getSimpleName()
					+ " , observer : " + this.getClass().getSimpleName()
					+ " , arg : " + arg);
		}
	}

}
