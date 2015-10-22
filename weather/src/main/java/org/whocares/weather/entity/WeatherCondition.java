package org.whocares.weather.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 对应文档中conf
 * 
 * @see<a href=
 *        'http://www.heweather.com/documents/api'>http://www.heweather.com/documents/api</a>
 * @author whocares
 * @version 0.1
 *
 */
public class WeatherCondition {
	/**
	 * 实时天气状况代码
	 */
	@JsonProperty("code")
	private String conditionCode;
	/**
	 * 实时天气状况描述
	 */
	@JsonProperty("txt")
	private String conditionDesc;
	/**
	 * 白天天气状况代码
	 */
	@JsonProperty("code_d")
	private String dayConditionCode;
	/**
	 * 白天天气状况描述
	 */
	@JsonProperty("txt_d")
	private String dayConditionDesc;
	/**
	 * 夜间天气状况代码
	 */
	@JsonProperty("code_n")
	private String nightConditionCode;
	/**
	 * 夜间天气状况描述
	 */
	@JsonProperty("txt_n")
	private String nightConditionDesc;

	public String getConditionCode() {
		return conditionCode;
	}

	public String getConditionDesc() {
		return conditionDesc;
	}

	public String getDayConditionCode() {
		return dayConditionCode;
	}

	public String getDayConditionDesc() {
		return dayConditionDesc;
	}

	public String getNightConditionCode() {
		return nightConditionCode;
	}

	public String getNightConditionDesc() {
		return nightConditionDesc;
	}

}