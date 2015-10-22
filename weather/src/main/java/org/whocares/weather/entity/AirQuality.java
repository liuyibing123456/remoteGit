package org.whocares.weather.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 对应文档中aqi
 * @see<a href='http://www.heweather.com/documents/api'>http://www.heweather.com/documents/api</a>
 * @author whocares
 * @version 0.1
 *
 */
public class AirQuality {
	/**
	 * 城市数据
	 */
	@JsonProperty("city")
	private CityAQI cityAQI;

	public CityAQI getCityAQI() {
		return cityAQI;
	}

	public static class CityAQI {
		/**
		 * 空气质量指数
		 */
		@JsonProperty("aqi")
		private String aqi;
		/**
		 * PM2.5 1小时平均值(ug/m³)
		 */
		@JsonProperty("pm25")
		private String pm25;
		/**
		 * PM10 1小时平均值(ug/m³)
		 */
		@JsonProperty("pm10")
		private String pm10;
		/**
		 * 二氧化硫1小时平均值(ug/m³)
		 */
		@JsonProperty("so2")
		private String so2;
		/**
		 * 二氧化氮1小时平均值(ug/m³)
		 */
		@JsonProperty("no2")
		private String no2;
		/**
		 * 一氧化碳1小时平均值(ug/m³)
		 */
		@JsonProperty("co")
		private String co;
		/**
		 * 臭氧1小时平均值(ug/m³)
		 */
		@JsonProperty("o3")
		private String o3;
		/**
		 * 空气质量类别
		 */
		@JsonProperty("qlty")
		private String qlty;

		public String getAqi() {
			return aqi;
		}

		public String getPm25() {
			return pm25;
		}

		public String getPm10() {
			return pm10;
		}

		public String getSo2() {
			return so2;
		}

		public String getNo2() {
			return no2;
		}

		public String getCo() {
			return co;
		}

		public String getO3() {
			return o3;
		}

		public String getQlty() {
			return qlty;
		}
	}
}
