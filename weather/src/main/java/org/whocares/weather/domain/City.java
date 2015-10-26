package org.whocares.weather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {
	@JsonProperty("provinceName")
	private String provinceName;
	
	@JsonProperty("province_cn")
	private String provinceCN;
	
	@JsonProperty("cityName")
	private String cityCN;
	
	@JsonProperty("cityCode")
	private String cityId;
	
	@JsonProperty("zipCode")
	private String cityPostCode;
	
	@JsonProperty("telAreaCode")
	private String cityTelCode;
	
	@JsonProperty("district_cn")
	private String districtCN;
	
	@JsonProperty("name_cn")
	private String nameCN;
	
	@JsonProperty("name_en")
	private String nameEN;
	
	@JsonProperty("area_id")
	private String areaId;
	
}
