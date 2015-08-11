package org.whocares.designPatterns.singleton;

/**
 * 饱汉模式
 * @author Thinkpad
 *
 */
public class SingletonOfFull {
	private static SingletonOfFull instance;
	
	private SingletonOfFull() {}
	
	public static SingletonOfFull getInstance() {
		if(instance == null) {
			instance = new SingletonOfFull();
		}
		return instance;
	}
}
