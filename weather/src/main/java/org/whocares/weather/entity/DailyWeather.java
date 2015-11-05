package org.whocares.weather.entity;

import java.util.Date;

import org.whocares.weather.jackson.NormalDateDeserializer;
import org.whocares.weather.jackson.NormalDateSerializer;
import org.whocares.weather.jackson.TimeDeserializer;
import org.whocares.weather.jackson.TimeSerializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 对应文档中daily_forecast
 * 
 * @see<a href=
 *        'http://www.heweather.com/documents/api'>http://www.heweather.com/documents/api</a>
 * @author whocares
 * @version 0.1
 *
 */
public class DailyWeather extends Weather {
	
	@JsonProperty("astro")
	private Astronomy astronomy;
	
	@JsonProperty("tmp")
	private Temperature temper;
	
	/*
	 * 覆盖父类Weather中setDate方法，当前类中以yyyy-MM-dd解析date
	 * 父类中以HH:mm 解析date
	 * @see org.whocares.weather.entity.Weather#setDate(java.util.Date)
	 */
	@Override
	@JsonProperty("date")
	@JsonDeserialize(using = NormalDateDeserializer.class)
	public void setDate(Date date) {
		super.setDate(date);
	}
	
	@Override
	@JsonSerialize(using = NormalDateSerializer.class)
	public Date getDate() {
		return super.getDate();
	}
	
	public Astronomy getAstronomy() {
		return astronomy;
	}

	public Temperature getTemper() {
		return temper;
	}

	public void setTemper(Temperature temper) {
		this.temper = temper;
	}

	public static class Temperature {
		/**
		 * 最高温度
		 */
		@JsonProperty("max")
		private String maxTemperature;
		/**
		 * 最低温度
		 */
		@JsonProperty("min")
		private String minTemperature;

		public String getMaxTemperature() {
			return maxTemperature;
		}

		public String getMinTemperature() {
			return minTemperature;
		}
	}
	
	public static class Astronomy {// 只有静态内部类才能序列化
		/**
		 * 日出时间
		 */
		@JsonProperty("sr")
		@JsonDeserialize(using = TimeDeserializer.class)
		@JsonSerialize(using = TimeSerializer.class)
		private Date sunRiseTime;
		/**
		 * 日落时间
		 */
		@JsonProperty("ss")
		@JsonDeserialize(using = TimeDeserializer.class)
		@JsonSerialize(using = TimeSerializer.class)
		private Date sunSetTime;

		public Date getSunRiseTime() {
			return sunRiseTime;
		}

		public Date getSunSetTime() {
			return sunSetTime;
		}
	}
	
	/*public class TimeDeserializer extends JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
			try {
				return formatter.parse(jp.getText());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}*/
}