package org.whocares.weather.entity;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.whocares.weather.jackson.DateDeserializer;

/**
 * 抽象父类
 * 
 * @author whocares
 * @version 0.1
 *
 */
public abstract class Weather {
	/**
	 * 相对湿度(%)
	 */
	@JsonProperty("hum")
	private String relativeHumidity;
	/**
	 * 降水量(mm)
	 */
	@JsonProperty("pcpn")
	private String precipitation;
	/**
	 * 气压
	 */
	@JsonProperty("pres")
	private String pressure;
	/**
	 * 温度
	 */
	@JsonProperty("tmp")
	private String temperature;
	/**
	 * 能见度(km)
	 */
	@JsonProperty("vis")
	private String visibility;
	/**
	 * 风力风向
	 */
	@JsonProperty("wind")
	private Wind wind;
	/**
	 * 降水概率
	 */
	@JsonProperty("pop")
	private String precipitationProbability;
	/**
	 * 时间
	 */
	@JsonProperty("date")
	@JsonDeserialize(using = DateDeserializer.class)
	private Date date;
	/**
	 * 天气情况信息
	 */
	@JsonProperty("cond")
	private WeatherCondition weatherCondition;

	public String getRelativeHumidity() {
		return relativeHumidity;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public String getPressure() {
		return pressure;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getVisibility() {
		return visibility;
	}

	public Wind getWind() {
		return wind;
	}

	public String getPrecipitationProbability() {
		return precipitationProbability;
	}

	public Date getDate() {
		return date;
	}

	public WeatherCondition getWeatherCondition() {
		return weatherCondition;
	}

	/*
	 * 允许子类覆盖该方法，以不同的格式解析date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}