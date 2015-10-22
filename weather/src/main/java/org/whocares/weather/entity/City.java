package org.whocares.weather.entity;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.whocares.weather.jackson.DateDeserializer;


/**
 * 对应文档中basic
 * 
 * @see<a href=
 *        'http://www.heweather.com/documents/api'>http://www.heweather.com/documents/api</a>
 * @author whocares
 * @version 0.1
 *
 */
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
	private String longitude;
	/**
	 * 数据更新时间,24小时制
	 */
	@JsonProperty("update")
	private DataUpdateTime dataUpdateTime;

	public String getCityId() {
		return cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCourtyName() {
		return courtyName;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public DataUpdateTime getDataUpdateTime() {
		return dataUpdateTime;
	}

	public static class DataUpdateTime {
		/**
		 * 数据更新的当地时间
		 */
		@JsonProperty("loc")
		@JsonDeserialize(using = DateDeserializer.class)
		private Date localTime;
		/**
		 * 数据更新的UTC时间
		 */
		@JsonProperty("utc")
		@JsonDeserialize(using = DateDeserializer.class)
		private Date utcTime;

		public Date getLocalTime() {
			return localTime;
		}

		public Date getUtcTime() {
			return utcTime;
		}
	}
}