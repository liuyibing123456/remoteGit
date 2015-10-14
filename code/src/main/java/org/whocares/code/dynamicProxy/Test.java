package org.whocares.code.dynamicProxy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Test {
	public static void main(String[] args) {
		Observer observer = null;
		Observable observable = null;
		Map<String, String> map = new HashMap<String, String>();
		String str1 = "1";
		String str2  = new String("1");
		Integer num1 = -127;
		Integer num2 = -127;
		System.out.println(str1 == str2);
		System.out.println(num1 == num2);
	}
}
