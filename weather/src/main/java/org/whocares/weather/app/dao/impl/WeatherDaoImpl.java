package org.whocares.weather.app.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.whocares.weather.app.dao.IWeatherDao;

@Repository("weatherDao")
public class WeatherDaoImpl implements IWeatherDao {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public String queryCityByCache(String cityId) {
		BoundValueOperations<String, String> cityBVOper = redisTemplate.boundValueOps("info.weather.city." + cityId);
		return cityBVOper.get();
	}

	@Override
	public String queryRealTimeWeatherByCache(String cityId) {
		BoundValueOperations<String, String> realTimeWeatherBVOper = redisTemplate.boundValueOps("info.weather.realTimeWeather." + cityId);
		return realTimeWeatherBVOper.get();
	}

	@Override
	public String queryAirQualityByCache(String cityId) {
		BoundValueOperations<String, String> airQualityBVOper = redisTemplate.boundValueOps("info.weather.airQuality." + cityId);
		return airQualityBVOper.get();
	}

	@Override
	public String querySuggestionByCache(String cityId) {
		BoundValueOperations<String, String> suggestionBVOper = redisTemplate.boundValueOps("info.weather.suggestion." + cityId);
		return suggestionBVOper.get();
	}

	@Override
	public String queryFutureWeatherByCache(String cityId) {
		BoundValueOperations<String, String> dailyWeatherBVOper = redisTemplate.boundValueOps("info.weather.dailyWeather." + cityId);
		return dailyWeatherBVOper.get();
	}

	@Override
	public String queryHourlyWeatherByCache(String cityId) {
		BoundValueOperations<String, String> hourlyWeatherBVOper = redisTemplate.boundValueOps("info.weather.hourlyWeather." + cityId);
		return hourlyWeatherBVOper.get();
	}

	@Override
	public String queryDailyWeatherByCache(String cityId, String date) {
		BoundValueOperations<String, String> dailyWeatherBVOper = redisTemplate.boundValueOps("info.weather.dailyWeather." + date + "." + cityId);
		return dailyWeatherBVOper.get();
	}

	@Override
	public String queryDailyTemperByCache(String cityId, String date) {
		BoundValueOperations<String, String> dailyTemperBVOper = redisTemplate.boundValueOps("info.weather.dailyWeather.temper." + date + "." + cityId);
		return dailyTemperBVOper.get();
	}

}
