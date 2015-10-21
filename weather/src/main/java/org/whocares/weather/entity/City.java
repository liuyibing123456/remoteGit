package org.whocares.weather.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class City {
	/**
	 * 城市ID
	 */
	@JsonProperty("id")
	private String cityId;
	/**
	 * 城市名称
	 */
	@JsonProperty("city")
	private String cityName;
	/**
	 * 国家名称
	 */
	@JsonProperty("cnty")
	private String courtyName;
	/**
	 * 纬度
	 */
	@JsonProperty("lat")
	private String latitude;
	/**
	 * 经度
	 */
	@JsonProperty("lon")
	private String  longitude;
	/**
	 * 数据更新时间,24小时制
	 */
	@JsonProperty("update")
	private Map<String, Date> updateTime = new HashMap<String, Date>();
}
