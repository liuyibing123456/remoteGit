package org.whocares.weather.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class City {
	/**
	 * ����ID
	 */
	@JsonProperty("id")
	private String cityId;
	/**
	 * ��������
	 */
	@JsonProperty("city")
	private String cityName;
	/**
	 * ��������
	 */
	@JsonProperty("cnty")
	private String courtyName;
	/**
	 * γ��
	 */
	@JsonProperty("lat")
	private String latitude;
	/**
	 * ����
	 */
	@JsonProperty("lon")
	private String  longitude;
	/**
	 * ���ݸ���ʱ��,24Сʱ��
	 */
	@JsonProperty("update")
	private Map<String, Date> updateTime = new HashMap<String, Date>();
}
