package org.whocares.weather.app.entity.city;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class City {

	private String districtId;

	private String districtEN;

	private String districtCN;

	private String cityCN;

	private String provinceCN;
	
	public City() {
		
	}
	
	public City(Builder builder) {
		this.districtId = builder.districtId;
		this.districtEN = builder.districtEN;
		this.districtCN = builder.districtCN;
	}
	
	public static class Builder {
		
		private String districtId;
		private String districtEN;
		private String districtCN;
		
		public Builder(String districtId, String districtEN, String districtCN) {
			this.districtId = districtId;
			this.districtCN = districtCN;
			this.districtEN = districtEN;
		}
		
		public City build() {
			return new City(this);
		}
		
	}

	public String getDistrictId() {
		return districtId;
	}

	public String getDistrictEN() {
		return districtEN;
	}

	public String getDistrictCN() {
		return districtCN;
	}

	public String getCityCN() {
		return cityCN;
	}

	public String getProvinceCN() {
		return provinceCN;
	}
}
