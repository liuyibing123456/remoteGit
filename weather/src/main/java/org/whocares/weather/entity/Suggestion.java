package org.whocares.weather.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 对应文档中suggestion
 * 
 * @see<a href=
 *        'http://www.heweather.com/documents/api'>http://www.heweather.com/documents/api</a>
 * @author whocares
 * @version 0.1
 *
 */
public class Suggestion {
	/**
	 * 穿衣指数
	 */
	@JsonProperty("drsg")
	private Sugg dressingSuggestion;
	/**
	 * 紫外线指数
	 */
	@JsonProperty("uv")
	private Sugg ultravioletSuggestion;
	/**
	 * 洗车指数
	 */
	@JsonProperty("cw")
	private Sugg carwashingSuggestion;
	/**
	 * 旅游指数
	 */
	@JsonProperty("trav")
	private Sugg tourismSuggestion;
	/**
	 * 感冒指数
	 */
	@JsonProperty("flu")
	private Sugg coldSuggestion;
	/**
	 * 运动指数
	 */
	@JsonProperty("sport")
	private Sugg sportSuggestion;
	/**
	 * 舒适指数
	 */
	@JsonProperty("comf")
	private Sugg comfortableSuggestion;

	public Sugg getDressingSuggestion() {
		return dressingSuggestion;
	}

	public Sugg getUltravioletSuggestion() {
		return ultravioletSuggestion;
	}

	public Sugg getCarwashingSuggestion() {
		return carwashingSuggestion;
	}

	public Sugg getTourismSuggestion() {
		return tourismSuggestion;
	}

	public Sugg getColdSuggestion() {
		return coldSuggestion;
	}

	public Sugg getSportSuggestion() {
		return sportSuggestion;
	}

	public Sugg getComfortableSuggestion() {
		return comfortableSuggestion;
	}

	public static class Sugg {
		/**
		 * 简介
		 */
		@JsonProperty("brf")
		private String brief;
		/**
		 * 详情
		 */
		@JsonProperty("txt")
		private String detail;

		public String getBrief() {
			return brief;
		}

		public String getDetail() {
			return detail;
		}
	}
}
