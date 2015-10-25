package org.whocares.weather.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.whocares.weather.entity.AirQuality;
import org.whocares.weather.entity.City;
import org.whocares.weather.entity.DailyWeather;
import org.whocares.weather.entity.HeWeather;
import org.whocares.weather.entity.HourlyWeather;
import org.whocares.weather.entity.RealTimeWeather;
import org.whocares.weather.entity.Suggestion;
import org.whocares.weather.service.IWeatherService;
import org.whocares.weather.util.GlobalUtils;

@Service("weatherService")
public class WeatherServiceImpl implements IWeatherService {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
	
	/*
	 * [cityId expried?] ——N——> {END}
	 *                  |
	 *                   ——Y——> [get weather success?] ——N——> {END}
	 *                                                |
	 *                                                 ——Y——> [put weather to redis] ——> {END}
	 */
	@Override
	public void queryWeatherInfoToCache(String cityId) {
		// 保存城市天气信息的超时标志，key不存在即超时，需重新获取该城市的天气信息
		BoundValueOperations<String,String> bvOper = redisTemplate.boundValueOps("url.weather.city." + cityId);
		String value = bvOper.get();
		
		LOGGER.debug("value : " + "url.weather.city." + cityId);
		LOGGER.debug("value : " + value);
		
		// value is not exsit or value is expired
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			/*BoundHashOperations<String, String, Object> cityBHOper = redisTemplate.boundHashOps("info.weather.city." + cityId);
			City city = heWeather.getCity();
			cityBHOper.put("cityId", city.getCityId());
			cityBHOper.put("cityName", city.getCityName());
			cityBHOper.put("courtyName", city.getCourtyName());
			cityBHOper.put("latitude", city.getLatitude());
			cityBHOper.put("longitude", city.getLongitude());
			cityBHOper.put("dataUpdateTime", city.getDataUpdateTime());*/
			
			BoundValueOperations<String, String> cityBVOper = redisTemplate.boundValueOps("info.weather.city." + cityId);
			City city = heWeather.getCity();
			cityBVOper.set(GlobalUtils.parseOject2Json(city));
			
			/*BoundHashOperations<String, String, Object> realTimeWeatherBHOper = redisTemplate.boundHashOps("info.weather.realTime." + cityId);
			RealTimeWeather realTimeWeather = heWeather.getRealTimeWeather();
			realTimeWeatherBHOper.put("sendibleTemperature", realTimeWeather.getSendibleTemperature());
			realTimeWeatherBHOper.put("relativeHumidity", realTimeWeather.getRelativeHumidity());
			realTimeWeatherBHOper.put("precipitation", realTimeWeather.getPrecipitation());
			realTimeWeatherBHOper.put("pressure", realTimeWeather.getPressure());
			realTimeWeatherBHOper.put("temperature", realTimeWeather.getTemperature());
			realTimeWeatherBHOper.put("visibility", realTimeWeather.getVisibility());
			realTimeWeatherBHOper.put("wind", realTimeWeather.getWind());
			realTimeWeatherBHOper.put("weatherCondition", realTimeWeather.getWeatherCondition());*/
			
			BoundValueOperations<String, String> realTimeWeatherBVOper = redisTemplate.boundValueOps("info.weather.realTimeWeather." + cityId);
			RealTimeWeather realTimeWeather = heWeather.getRealTimeWeather();
			realTimeWeatherBVOper.set(GlobalUtils.parseOject2Json(realTimeWeather));
			
			/*BoundHashOperations<String, String, Object> airQualityBHOper = redisTemplate.boundHashOps("info.weather.airQuality." + cityId);
			AirQuality airQuality = heWeather.getAirQuality();
			airQualityBHOper.put("aqi", airQuality.getCityAQI().getAqi());
			airQualityBHOper.put("co", airQuality.getCityAQI().getCo());
			airQualityBHOper.put("no2", airQuality.getCityAQI().getNo2());
			airQualityBHOper.put("o3", airQuality.getCityAQI().getO3());
			airQualityBHOper.put("pm10", airQuality.getCityAQI().getPm10());
			airQualityBHOper.put("pm25", airQuality.getCityAQI().getPm25());
			airQualityBHOper.put("qlty", airQuality.getCityAQI().getQlty());
			airQualityBHOper.put("so2", airQuality.getCityAQI().getSo2());*/
			
			BoundValueOperations<String, String> airQualityBVOper = redisTemplate.boundValueOps("info.weather.airQuality." + cityId);
			AirQuality airQuality = heWeather.getAirQuality();
			airQualityBVOper.set(GlobalUtils.parseOject2Json(airQuality));
			
			/*BoundHashOperations<String, String, Object> suggestionBHOper = redisTemplate.boundHashOps("info.weather.suggestion." + cityId);
			Suggestion suggestion = heWeather.getSuggestion();
			suggestionBHOper.put("carWashingSuggestion", suggestion.getCarwashingSuggestion());
			suggestionBHOper.put("coldSuggestion", suggestion.getColdSuggestion());
			suggestionBHOper.put("comfortableSuggestion", suggestion.getComfortableSuggestion());
			suggestionBHOper.put("dressingSuggestion", suggestion.getDressingSuggestion());
			suggestionBHOper.put("sportSuggestion", suggestion.getSportSuggestion());
			suggestionBHOper.put("tourismSuggestion", suggestion.getTourismSuggestion());
			suggestionBHOper.put("ultravioletSuggestion", suggestion.getUltravioletSuggestion());*/
			
			BoundValueOperations<String, String> suggestionBVOper = redisTemplate.boundValueOps("info.weather.suggestion." + cityId);
			Suggestion suggestion = heWeather.getSuggestion();
			suggestionBVOper.set(GlobalUtils.parseOject2Json(suggestion));
			
			/*List<DailyWeather> dailyWeatherList = heWeather.getDailyWeatherList();
			for (DailyWeather dailyWeather : dailyWeatherList) {
				BoundHashOperations<String, String, Object> dailyWeatherBHOper = redisTemplate.boundHashOps("info.weather.dailyWeather." + dailyWeather.getDate() + "." + cityId);
				dailyWeatherBHOper.put("astronomy", dailyWeather.getAstronomy());
				dailyWeatherBHOper.put("weatherCondition", dailyWeather.getWeatherCondition());
				dailyWeatherBHOper.put("date", dailyWeather.getDate());
				dailyWeatherBHOper.put("relativeHumidity", dailyWeather.getRelativeHumidity());
				dailyWeatherBHOper.put("precipitation", dailyWeather.getPrecipitation());
				dailyWeatherBHOper.put("precipitationProbability", dailyWeather.getPrecipitationProbability());
				dailyWeatherBHOper.put("pressure", dailyWeather.getPressure());
				dailyWeatherBHOper.put("temper", dailyWeather.getTemper());
				dailyWeatherBHOper.put("visibility", dailyWeather.getVisibility());
				dailyWeatherBHOper.put("wind", dailyWeather.getWind());
			}*/
			
			List<DailyWeather> dailyWeatherList = heWeather.getDailyWeatherList();
			for (DailyWeather dailyWeather : dailyWeatherList) {
				BoundValueOperations<String, String> dailyWeatherBVOper = redisTemplate.boundValueOps("info.weather.dailyWeather." + dailyWeather.getDate() + "." + cityId);
				dailyWeatherBVOper.set(GlobalUtils.parseOject2Json(dailyWeather));
			}
			
			/*List<HourlyWeather> hourlyWeatherList = heWeather.getHourlyWeatherList();
			for (HourlyWeather hourlyWeather : hourlyWeatherList) {
				BoundHashOperations<String, String, Object> hourlyWeatherBHOper = redisTemplate.boundHashOps("info.weather.hourlyWeather." + hourlyWeather.getDate() + "." + cityId);
				hourlyWeatherBHOper.put("date", hourlyWeather.getDate());
				hourlyWeatherBHOper.put("relativeHumidity", hourlyWeather.getRelativeHumidity());
				hourlyWeatherBHOper.put("precipitationProbability", hourlyWeather.getPrecipitationProbability());
				hourlyWeatherBHOper.put("pressure", hourlyWeather.getPressure());
				hourlyWeatherBHOper.put("temperature", hourlyWeather.getTemperature());
				hourlyWeatherBHOper.put("wind", hourlyWeather.getWind());
			}*/
			
			List<HourlyWeather> hourlyWeatherList = heWeather.getHourlyWeatherList();
			for (HourlyWeather hourlyWeather : hourlyWeatherList) {
				BoundValueOperations<String, String> hourlyWeatherBHOper = redisTemplate.boundValueOps("info.weather.hourlyWeather." + hourlyWeather.getDate() + "." + cityId);
				hourlyWeatherBHOper.set(GlobalUtils.parseOject2Json(hourlyWeather));
			}
			
			long timeout = Long.valueOf(GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "timeout.weather.city"));
			// 给超时标志的value值赋为城市id，无任何意义
			bvOper.set(cityId, timeout, TimeUnit.SECONDS);
		}
	}

	@Override
	public String queryCityByCache(String cityId) {
		BoundValueOperations<String, String> cityBVOper = redisTemplate.boundValueOps("info.weather.city." + cityId);
		String value = cityBVOper.get();
		
		// 以下if语句是第一次访问时由于网络或redis问题，缓存中无数据情况的补救措施，正常情况下不会执行
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			City city = heWeather.getCity();
			value = GlobalUtils.parseOject2Json(city);
			cityBVOper.set(value);
		}
		return value;
	}
	
	@Override
	public String queryRealTimeWeatherByCache(String cityId) {
		BoundValueOperations<String, String> realTimeWeatherBVOper = redisTemplate.boundValueOps("info.weather.realTimeWeather." + cityId);
		String value = realTimeWeatherBVOper.get();
		
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			RealTimeWeather realTimeWeather = heWeather.getRealTimeWeather();
			value = GlobalUtils.parseOject2Json(realTimeWeather);
			realTimeWeatherBVOper.set(value);
		}
		return value;
	}
	
	@Override
	public String queryAirQualityByCache(String cityId) {
		BoundValueOperations<String, String> airQualityBVOper = redisTemplate.boundValueOps("info.weather.airQuality." + cityId);
		String value = airQualityBVOper.get();
		
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			AirQuality airQuality = heWeather.getAirQuality();
			value = GlobalUtils.parseOject2Json(airQuality);
			airQualityBVOper.set(value);
		}
		return value;
	}
	
	@Override
	public String querySuggestionByCache(String cityId) {
		BoundValueOperations<String, String> suggestionBVOper = redisTemplate.boundValueOps("info.weather.suggestion." + cityId);
		String value = suggestionBVOper.get();
		
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			Suggestion suggestion = heWeather.getSuggestion();
			value = GlobalUtils.parseOject2Json(suggestion);
			suggestionBVOper.set(value);
		}
		return value;
	}
	
	@Override
	public String queryDailyWeatherByCache(String cityId) {
		BoundValueOperations<String, String> dailyWeatherBVOper = redisTemplate.boundValueOps("info.weather.dailyWeather." + cityId);
		String value = dailyWeatherBVOper.get();
		
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			List<DailyWeather> dailyWeatherList = heWeather.getDailyWeatherList();
			value = GlobalUtils.parseOject2Json(dailyWeatherList);
			dailyWeatherBVOper.set(value);
		}
		return value;
	}
	
	@Override
	public String queryHourlyWeatherByCache(String cityId) {
		BoundValueOperations<String, String> hourlyWeatherBVOper = redisTemplate.boundValueOps("info.weather.hourlyWeather." + cityId);
		String value = hourlyWeatherBVOper.get();
		
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			List<HourlyWeather> hourlyWeatherList = heWeather.getHourlyWeatherList();
			value = GlobalUtils.parseOject2Json(hourlyWeatherList);
			hourlyWeatherBVOper.set(value);
		}
		return value;
	}
	
	private HeWeather getObjectFromHttp(String cityId) {
		String url = GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "url.weather.city");
		
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("apikey", GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "apikey"));
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("city", cityId);
		
		String value = GlobalUtils.httpConnection(url, heads, params);
		LOGGER.debug("responseStr : " + value);
		
		value = value.substring(value.indexOf("[") + 1, value.lastIndexOf("]"));
		LOGGER.debug("after dealing responseStr : " + value);
		
		HeWeather heWeather = GlobalUtils.parseResponseJson(value, HeWeather.class);
		
		// String successCode = "ok";
		String successCode = GlobalUtils.getPropVal(GlobalUtils.ERRORCODE_PROPS, "error.info.ok");
		if (!successCode.equals(heWeather.getStatus())) {
			LOGGER.debug("status : " + heWeather.getStatus());
			return null;
		}
		return heWeather;
	}

}
