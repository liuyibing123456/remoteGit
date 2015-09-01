package org.whocares.designPatterns.observer;

import java.util.Observable;

public class Subject extends Observable {
	private String name;
	private int type;

	public Subject() {
		
	}
	
	public Subject(String name, int type) {
		this.name = name;
		this.type = type;
		this.notifyObservers();
	}

	public void setName(String name) {
		this.name = name;
		this.notifyObservers(name);
	}

	public void setType(int type) {
		this.type = type;
		this.notifyObservers(type);
	}
	
	@Override
	public void setChanged() {
		super.setChanged();
	}
	
}
