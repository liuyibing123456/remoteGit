package org.whocares.weather.app.dao.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.whocares.weather.app.dao.ICityDao;
import org.whocares.weather.util.GlobalUtils;

@Repository("cityDao")
public class CityDaoImpl implements ICityDao {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public String queryProvincesByCache() {
		BoundSetOperations<String, String> provinceBSOper = this.redisTemplate.boundSetOps("info.city.provinceCN");
		Set<String> set = provinceBSOper.members();
		return GlobalUtils.parseOject2Json(set);
	}

	@Override
	public String queryCitysByCache(String province) {
		BoundSetOperations<String, String> cityBSOper = this.redisTemplate.boundSetOps("info.city.cityCN." + province);
		Set<String> set = cityBSOper.members();
		return GlobalUtils.parseOject2Json(set);
	}

	@Override
	public String queryDistrictsByCache(String province, String city) {
		BoundSetOperations<String, String> districtBSOper = this.redisTemplate.boundSetOps("info.city.districtCN." + province + "." + city);
		Set<String> set = districtBSOper.members();
		return GlobalUtils.parseOject2Json(set);
	}

	@Override
	public void cacheProvinces(String provinvce) {
		BoundSetOperations<String, String> provinceBSOper = this.redisTemplate.boundSetOps("info.city.provinceCN");
		provinceBSOper.add(provinvce);
	}
	
	@Override
	public void cacheCitys(String province, String city) {
		BoundSetOperations<String, String> cityBSOper = this.redisTemplate.boundSetOps("info.city.cityCN." + province);
		cityBSOper.add(city);
	}
	
	@Override
	public void cacheDistricts(String province, String city, String district) {
		BoundSetOperations<String, String> districtBSOper = this.redisTemplate.boundSetOps("info.city.districtCN." + province + "." + city);
		districtBSOper.add(district);
	}

}
