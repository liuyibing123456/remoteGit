package org.whocares.designPatterns.multition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Multition {
	
	private final static int MAX_NUM = 2;
	
	public static final int FIRST_INSTANCE = 0;
	
	public static final int SECOND_INSTANCE = 1;
	
	private static List<Multition> list = new ArrayList<Multition>(MAX_NUM);
	
	private Multition() {
	}
	
	static {
		for (int i = 0; i < MAX_NUM; i++) {
			list.add(new Multition());
		}
	}
	
	public static Multition getInstance() {
		int random = new Random().nextInt(MAX_NUM);
		return list.get(random);
	}
	
	/**
	 * 
	 * @param index 
	 * @return
	 */
	public static Multition getInstance(int index) {
		if(index == FIRST_INSTANCE || index == SECOND_INSTANCE) {
			return list.get(index);
		} else {
			throw new RuntimeException("bad arg");
		}
	}
}
