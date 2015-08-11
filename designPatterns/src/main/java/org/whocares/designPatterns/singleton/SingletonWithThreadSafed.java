package org.whocares.designPatterns.singleton;
/**
 * 线程安全饱汉模式
 * @author Thinkpad
 *
 */
public class SingletonWithThreadSafed {
	
	private static SingletonWithThreadSafed instance;
	
	private SingletonWithThreadSafed(){}
	
	public static SingletonWithThreadSafed getInstance() {
		if(instance == null) {
			synchronized (SingletonWithThreadSafed.class) {
				if(instance == null) {
					new SingletonWithThreadSafed();
				}
			}
		}
		return instance;
	}
}
