package org.whocares.designPatterns.strategy;

public class Context {
	private Strategy strategy;
	
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void operate() {
		this.strategy.operate();
	}
}
