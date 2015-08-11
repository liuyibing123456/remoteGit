package org.whocares.designPatterns.templateMethod;

/**
 * 模板方法模式
 * 定义一个抽象父类，部分逻辑以具体方法实现，部分逻辑以抽象方法延迟到子类方法实现；
 * 用钩子方法赋予子类更大的灵活性，最终将所有方法汇总成final类型的模板方法。
 * @author Thinkpad
 *
 */
public abstract class Beverage {

	/**
	 * 泡饮料
	 */
	protected abstract void brew();

	/**
	 * 加入调料
	 */
	protected abstract void addCondiments();

	/**
	 * 钩子方法，是否需要加入调料
	 * 
	 * @return true 需要加入调料<br/>
	 *                 false 不需要加入调料
	 */
	protected abstract boolean addCondimentsHooks();

	private void boilWater() {
		System.out.println("烧水.....");
	}

	private void PourInCup() {
		System.out.println("倒入杯子中......");
	}

	/**
	 * 制作饮料模板，final方法不需要子类覆写
	 */
	public final void makeBeverageTemplate() {
		this.boilWater();
		this.brew();
		this.PourInCup();
		if (addCondimentsHooks()) {
			this.addCondiments();
		}
	}
}
