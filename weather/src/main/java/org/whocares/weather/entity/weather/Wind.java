package org.whocares.weather.entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 对应文档中wind
 * 
 * @see<a href=
 *        'http://www.heweather.com/documents/api'>http://www.heweather.com/documents/api</a>
 * @author whocares
 * @version 0.1
 *
 */
public class Wind {
	/**
	 * 风速(Kmph)
	 */
	@JsonProperty("spd")
	private String windSpeed;
	/**
	 * 风力等级
	 */
	@JsonProperty("sc")
	private String windScale;
	/**
	 * 风向(角度)
	 */
	@JsonProperty("deg")
	private String windDegree;
	/**
	 * 风向(方向)
	 */
	@JsonProperty("dir")
	private String windDirection;

	public String getWindSpeed() {
		return windSpeed;
	}

	public String getWindScale() {
		return windScale;
	}

	public String getWindDegree() {
		return windDegree;
	}

	public String getWindDirection() {
		return windDirection;
	}
}