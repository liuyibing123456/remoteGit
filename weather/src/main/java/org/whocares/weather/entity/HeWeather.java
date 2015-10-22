package org.whocares.weather.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class HeWeather {

	/**
	 * 状态码
	 */
	@JsonProperty("status")
	private String status;
	/**
	 * 城市基本信息
	 */
	@JsonProperty("basic")
	private City city;
	/**
	 * 实况天气
	 */
	@JsonProperty("now")
	private RealTimeWeather realTimeWeather;
	/**
	 * 天气预报（/天）
	 */
	@JsonProperty("daily_forecast")
	private List<DailyWeather> dailyWeatherList;
	/**
	 * 天气预报（/三小时）
	 */
	@JsonProperty("hourly_forecast")
	private List<HourlyWeather> hourlyWeatherList;
	/**
	 * 空气质量指数
	 */
	@JsonProperty("aqi")
	private AirQuality airQuality;
	/**
	 * 生活指数
	 */
	@JsonProperty("suggestion")
	private Suggestion suggestion;
}
