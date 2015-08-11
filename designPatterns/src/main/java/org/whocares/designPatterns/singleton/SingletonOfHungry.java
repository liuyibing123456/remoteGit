package org.whocares.designPatterns.singleton;
/**
 * 饿汉模式
 * @author Thinkpad
 *
 */
public class SingletonOfHungry {
	
	private static SingletonOfHungry instance = new SingletonOfHungry();
	
	private SingletonOfHungry(){}
	
	public static SingletonOfHungry getInstance() {
		return instance;
	}
}
