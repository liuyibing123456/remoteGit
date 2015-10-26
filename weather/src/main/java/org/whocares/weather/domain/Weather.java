package org.whocares.weather.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
	private String city;
	
	@JsonProperty("pinyin")
	private String citySpell;
	
	@JsonProperty("citycode")
	private String cityCode;
	
	private Date date;
	
	private Date time;
	
	private String postCode;
	
	private String longitude;
	
	private String latitude;
	
	private String altitude;
	
	private String weather;
	
	private String temp;
	
	@JsonProperty("l_temp")
	private String lowTemp;
	
	@JsonProperty("h_temp")
	private String highTemp;
	
	@JsonProperty("WD")
	private String windDirection;
	
	@JsonProperty("SD")
	private String windSpeed;
	
	@JsonProperty("sunrise")
	private Date sunRiseTime;
	
	@JsonProperty("sunset")
	private Date sunsetTime;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCitySpell() {
		return citySpell;
	}

	public void setCitySpell(String citySpell) {
		this.citySpell = citySpell;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
	}

	public String getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Date getSunRiseTime() {
		return sunRiseTime;
	}

	public void setSunRiseTime(Date sunRiseTime) {
		this.sunRiseTime = sunRiseTime;
	}

	public Date getSunsetTime() {
		return sunsetTime;
	}

	public void setSunsetTime(Date sunsetTime) {
		this.sunsetTime = sunsetTime;
	}

}
