package org.whocares.weather.domain;

public class CityInfoResponsePackage extends ResponsePackage<City> {

	public String getRetMsg() {
		return super.getErrMsg();
	}
	
	public void setRetMsg(String errMsg) {
		super.setErrMsg(errMsg);
	}
	
	public City getRetData() {
		return super.getRetData();
	}

	public void setRetData(City retData) {
		super.setRetData(retData);
	}
}
