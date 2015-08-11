package org.whocares.designPatterns.templateMethod;

import org.junit.Test;
import org.whocares.designPatterns.templateMethod.Beverage;
import org.whocares.designPatterns.templateMethod.BlackTea;
import org.whocares.designPatterns.templateMethod.Coffee;
import org.whocares.designPatterns.templateMethod.GreenTea;

public class BeverageTest {

	@Test
	public void testBlackTea() {
		Beverage beverage = new BlackTea();
		
		beverage.makeBeverageTemplate();
	}
	
	@Test
	public void testGreenTea() {
		Beverage beverage = new GreenTea();
		
		beverage.makeBeverageTemplate();
	}
	@Test
	public void testCoffee() {
		Beverage beverage = new Coffee();
		
		beverage.makeBeverageTemplate();
	}

}
