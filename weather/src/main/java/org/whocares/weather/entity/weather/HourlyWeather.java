package org.whocares.weather.entity.weather;

import java.util.Date;

import org.whocares.weather.jackson.DateDeserializer;
import org.whocares.weather.jackson.DateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 对应文档中hourly_forecast
 * 
 * @see<a href=
 *        'http://www.heweather.com/documents/api'>http://www.heweather.com/documents/api</a>
 * @author whocares
 * @version 0.1
 *
 */
public class HourlyWeather extends Weather {
	
	/*
	 * 覆盖父类Weather中的setDate方法，以yyyy-MM-dd HH:mm格式解析date
	 * 注意：暂不清楚为什么子类的解析规则无法继承父类，故重新覆盖并解析
	 * @see org.whocares.weather.entity.Weather#setDate(java.util.Date)
	 */
	@JsonDeserialize(using = DateDeserializer.class)
	public void setDate(Date date) {
		super.setDate(date);
	}
	
	@JsonSerialize(using = DateSerializer.class)
	public Date getDate() {
		return super.getDate();
	}
}
