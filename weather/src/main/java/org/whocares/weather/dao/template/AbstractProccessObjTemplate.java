package org.whocares.weather.dao.template;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.whocares.weather.entity.HeWeather;
import org.whocares.weather.util.GlobalUtils;

public abstract class AbstractProccessObjTemplate {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	protected RedisTemplate<String, String> getRedisTemplate() {
		return this.redisTemplate;
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractProccessObjTemplate.class);
	
	public final void template(String cityId) {
		if (!this.isCached(cityId)) {
			LOGGER.info("redis key is not exsit or is expired, prepare to query weather info through http...");
			
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			if (heWeather == null) {
				LOGGER.error("get instance of {} failed, process stopped.", HeWeather.class);
				return;
			}
			
			try {
				this.cacheCityInfo(heWeather, cityId);
				this.cacheRealTimeInfo(heWeather, cityId);
				this.cacheDailyInfo(heWeather, cityId);
				this.cacheHourlyInfo(heWeather, cityId);
				this.cacheAirQualityInfo(heWeather, cityId);
				this.cacheSuggestionInfo(heWeather, cityId);
				
				this.setExpired(cityId);
			} catch (RuntimeException e) {
				LOGGER.error("save instance into cache failed, process stopped.", e);
				return;
			}
		}
	}
	
	protected abstract void cacheCityInfo(HeWeather heWeather, String cityId) throws RuntimeException;
	
	protected abstract void cacheRealTimeInfo(HeWeather heWeather, String cityId) throws RuntimeException;
	
	protected abstract void cacheDailyInfo(HeWeather heWeather, String cityId) throws RuntimeException;

	protected abstract void cacheHourlyInfo(HeWeather heWeather, String cityId) throws RuntimeException;

	protected abstract void cacheAirQualityInfo(HeWeather heWeather, String cityId) throws RuntimeException;
	
	protected abstract void cacheSuggestionInfo(HeWeather heWeather, String cityId) throws RuntimeException;
	
	
	private boolean isCached(String cityId) {
		// 保存城市天气信息的超时标志，key不存在即超时，需重新获取该城市的天气信息
		BoundValueOperations<String,String> bvOper = redisTemplate.boundValueOps("url.weather.city." + cityId);
		return !(bvOper.get() == null);
	}
	
	private void setExpired(String cityId) {
		BoundValueOperations<String,String> bvOper = redisTemplate.boundValueOps("url.weather.city." + cityId);
		long timeout = Long.valueOf(GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "timeout.weather.city"));
		// 给超时标志的value值赋为城市id，无任何意义
		bvOper.set(cityId, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * 
	 * @param cityId
	 * @return
	 */
	private HeWeather getObjectFromHttp(String cityId) {
		String url = GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "url.weather.city");
		
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("apikey", GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "apikey"));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("cityid", cityId);
		
		LOGGER.debug("city : " + cityId);
		
		String value = null;
		try {
			value = GlobalUtils.httpConnection(url, heads, params);//GlobalUtils.readFileAsString("weather.json");//
		} catch (RuntimeException e) {
			return null;
		} finally {
			LOGGER.info("responseStr : " + value);
		}
		
		value = value.substring(value.indexOf("[") + 1, value.lastIndexOf("]"));
		LOGGER.debug("after dealing responseStr : " + value);
		
		HeWeather heWeather = GlobalUtils.parseResponseJson(value, HeWeather.class);
		
		// String successCode = "ok";
		String successCode = GlobalUtils.getPropVal(GlobalUtils.ERRORCODE_PROPS, "error.info.ok");
		if (!successCode.equals(heWeather.getStatus())) {
			LOGGER.error("status : " + heWeather.getStatus());
			return null;
		}
		return heWeather;
	}
}
