package org.whocares.weather.app.dao;

public interface ICityDao {
	
	String queryProvincesByCache();
	
	String queryCitysByCache(String province);
	
	String queryDistrictsByCache(String province, String city);
	
	void cacheProvinces(String provinvce);

	void cacheCitys(String province, String city);
	
	void cacheDistricts(String province, String city, String district);
	
}
