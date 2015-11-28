package org.whocares.weather.app.entity.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 对应文档中now
 * 
 * @see<a href=
 *        'http://www.heweather.com/documents/api'>http://www.heweather.com/documents/api</a>
 * @author whocares
 * @version 0.1
 *
 */
public class RealTimeWeather extends Weather {

	/**
	 * 体感温度
	 */
	@JsonProperty("fl")
	private String sendibleTemperature;

	public String getSendibleTemperature() {
		return sendibleTemperature;
	}
}
