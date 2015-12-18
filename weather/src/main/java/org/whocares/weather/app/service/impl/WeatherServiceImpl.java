package org.whocares.weather.app.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whocares.weather.app.dao.IWeatherDao;
import org.whocares.weather.app.dao.template.AbstractProccessObjTemplate;
import org.whocares.weather.app.service.IWeatherService;
import org.whocares.weather.util.GlobalUtils;

@Service("weatherService")
public class WeatherServiceImpl implements IWeatherService {

	@Autowired
	private AbstractProccessObjTemplate proccessObjTemplate;
	
	@Autowired
	private IWeatherDao weatherDao;
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
	
	@Override
	public String queryCityInfo(String cityId) {
//		this.queryWeatherInfoToCache(cityId);
		this.proccessObjTemplate.template(cityId);
		return weatherDao.queryCityByCache(cityId);
	}
	
	@Override
	public String queryRealTimeWeatherInfo(String cityId) {
//		this.queryWeatherInfoToCache(cityId);
		this.proccessObjTemplate.template(cityId);
		return weatherDao.queryRealTimeWeatherByCache(cityId);
	}
	
	@Override
	public String queryAirQualityInfo(String cityId) {
//		this.queryWeatherInfoToCache(cityId);
		this.proccessObjTemplate.template(cityId);
		return weatherDao.queryAirQualityByCache(cityId);
	}
	
	@Override
	public String querySuggestionInfo(String cityId) {
//		this.queryWeatherInfoToCache(cityId);
		this.proccessObjTemplate.template(cityId);
		return weatherDao.querySuggestionByCache(cityId);
	}
	
	@Override
	public String queryFutureWeatherInfo(String cityId) {
//		this.queryWeatherInfoToCache(cityId);
		this.proccessObjTemplate.template(cityId);
		return weatherDao.queryFutureWeatherByCache(cityId);
	}
	
	@Override
	public String queryHistoryWeatherInfo(String cityId, Date beginDate, int days) {
//		this.queryWeatherInfoToCache(cityId);
		this.proccessObjTemplate.template(cityId);
		return this.queryHistoryWeatherByCache(cityId, beginDate, days);
	}
	
	@Override
	public String queryDailyTemperInfo(String cityId, Date beginDate, int days) {
		this.proccessObjTemplate.template(cityId);
		return this.queryDailyTemperByCache(cityId, beginDate, days);
	}
	
	@Override
	public String queryHourlyWeatherInfo(String cityId) {
//		this.queryWeatherInfoToCache(cityId);
		this.proccessObjTemplate.template(cityId);
		return weatherDao.queryHourlyWeatherByCache(cityId);
	}
	
	/*
	 * [cityId expried?] ——N——> {END}
	 *                  |
	 *                   ——Y——> [get weather success?] ——N——> {END}
	 *                                                |
	 *                                                 ——Y——> [put weather to redis] ——> {END}
	 */
	/*private void queryWeatherInfoToCache(String cityId) {
		// 保存城市天气信息的超时标志，key不存在即超时，需重新获取该城市的天气信息
		BoundValueOperations<String,String> bvOper = redisTemplate.boundValueOps("url.weather.city." + cityId);
		String value = bvOper.get();
		
		LOGGER.debug("==================== cityId : " + "url.weather.city." + cityId);
		LOGGER.debug("==================== value : " + value);
		
		// value is not exsit or value is expired
		if (value == null) {
			LOGGER.info("redis key is not exsit or is expired, prepare to query info through http...");
			
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			if (heWeather == null) {
				return;
			}
			
			String date = GlobalUtils.getCurrentDate();
			
//			BoundHashOperations<String, String, Object> cityBHOper = redisTemplate.boundHashOps("info.weather.city." + cityId);
//			City city = heWeather.getCity();
//			cityBHOper.put("cityId", city.getCityId());
//			cityBHOper.put("cityName", city.getCityName());
//			cityBHOper.put("courtyName", city.getCourtyName());
//			cityBHOper.put("latitude", city.getLatitude());
//			cityBHOper.put("longitude", city.getLongitude());
//			cityBHOper.put("dataUpdateTime", city.getDataUpdateTime());
			
			BoundValueOperations<String, String> cityBVOper = redisTemplate.boundValueOps("info.weather.city." + date + "." + cityId);
			City city = heWeather.getCity();
			cityBVOper.set(GlobalUtils.parseOject2Json(city));
			
			BoundHashOperations<String, String, Object> realTimeWeatherBHOper = redisTemplate.boundHashOps("info.weather.realTime." + cityId);
			RealTimeWeather realTimeWeather = heWeather.getRealTimeWeather();
			realTimeWeatherBHOper.put("sendibleTemperature", realTimeWeather.getSendibleTemperature());
			realTimeWeatherBHOper.put("relativeHumidity", realTimeWeather.getRelativeHumidity());
			realTimeWeatherBHOper.put("precipitation", realTimeWeather.getPrecipitation());
			realTimeWeatherBHOper.put("pressure", realTimeWeather.getPressure());
			realTimeWeatherBHOper.put("temperature", realTimeWeather.getTemperature());
			realTimeWeatherBHOper.put("visibility", realTimeWeather.getVisibility());
			realTimeWeatherBHOper.put("wind", realTimeWeather.getWind());
			realTimeWeatherBHOper.put("weatherCondition", realTimeWeather.getWeatherCondition());
			
			BoundValueOperations<String, String> realTimeWeatherBVOper = redisTemplate.boundValueOps("info.weather.realTimeWeather." + date + "." + cityId);
			RealTimeWeather realTimeWeather = heWeather.getRealTimeWeather();
			realTimeWeatherBVOper.set(GlobalUtils.parseOject2Json(realTimeWeather));
			
			BoundHashOperations<String, String, Object> airQualityBHOper = redisTemplate.boundHashOps("info.weather.airQuality." + cityId);
			AirQuality airQuality = heWeather.getAirQuality();
			airQualityBHOper.put("aqi", airQuality.getCityAQI().getAqi());
			airQualityBHOper.put("co", airQuality.getCityAQI().getCo());
			airQualityBHOper.put("no2", airQuality.getCityAQI().getNo2());
			airQualityBHOper.put("o3", airQuality.getCityAQI().getO3());
			airQualityBHOper.put("pm10", airQuality.getCityAQI().getPm10());
			airQualityBHOper.put("pm25", airQuality.getCityAQI().getPm25());
			airQualityBHOper.put("qlty", airQuality.getCityAQI().getQlty());
			airQualityBHOper.put("so2", airQuality.getCityAQI().getSo2());
			
			BoundValueOperations<String, String> airQualityBVOper = redisTemplate.boundValueOps("info.weather.airQuality." +date + "." + cityId);
			AirQuality airQuality = heWeather.getAirQuality();
			airQualityBVOper.set(GlobalUtils.parseOject2Json(airQuality));
			
			BoundHashOperations<String, String, Object> suggestionBHOper = redisTemplate.boundHashOps("info.weather.suggestion." + cityId);
			Suggestion suggestion = heWeather.getSuggestion();
			suggestionBHOper.put("carWashingSuggestion", suggestion.getCarwashingSuggestion());
			suggestionBHOper.put("coldSuggestion", suggestion.getColdSuggestion());
			suggestionBHOper.put("comfortableSuggestion", suggestion.getComfortableSuggestion());
			suggestionBHOper.put("dressingSuggestion", suggestion.getDressingSuggestion());
			suggestionBHOper.put("sportSuggestion", suggestion.getSportSuggestion());
			suggestionBHOper.put("tourismSuggestion", suggestion.getTourismSuggestion());
			suggestionBHOper.put("ultravioletSuggestion", suggestion.getUltravioletSuggestion());
			
			BoundValueOperations<String, String> suggestionBVOper = redisTemplate.boundValueOps("info.weather.suggestion." + date + "." + cityId);
			Suggestion suggestion = heWeather.getSuggestion();
			suggestionBVOper.set(GlobalUtils.parseOject2Json(suggestion));
			
			List<DailyWeather> dailyWeatherList = heWeather.getDailyWeatherList();
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
			}
			
			List<DailyWeather> dailyWeatherList = heWeather.getDailyWeatherList();
			
			BoundValueOperations<String, String> futureWeatherBVOper = redisTemplate.boundValueOps("info.weather.dailyWeather." + cityId);
			futureWeatherBVOper.set(GlobalUtils.parseOject2Json(dailyWeatherList));
			
			for (DailyWeather dailyWeather : dailyWeatherList) {
				BoundValueOperations<String, String> dailyWeatherBVOper = redisTemplate.boundValueOps("info.weather.dailyWeather." + GlobalUtils.parseDate("yyyy-MM-dd", dailyWeather.getDate()) + "." + cityId);
				dailyWeatherBVOper.set(GlobalUtils.parseOject2Json(dailyWeather));
			}
			
			List<HourlyWeather> hourlyWeatherList = heWeather.getHourlyWeatherList();
			for (HourlyWeather hourlyWeather : hourlyWeatherList) {
				BoundHashOperations<String, String, Object> hourlyWeatherBHOper = redisTemplate.boundHashOps("info.weather.hourlyWeather." + hourlyWeather.getDate() + "." + cityId);
				hourlyWeatherBHOper.put("date", hourlyWeather.getDate());
				hourlyWeatherBHOper.put("relativeHumidity", hourlyWeather.getRelativeHumidity());
				hourlyWeatherBHOper.put("precipitationProbability", hourlyWeather.getPrecipitationProbability());
				hourlyWeatherBHOper.put("pressure", hourlyWeather.getPressure());
				hourlyWeatherBHOper.put("temperature", hourlyWeather.getTemperature());
				hourlyWeatherBHOper.put("wind", hourlyWeather.getWind());
			}
			
			List<HourlyWeather> hourlyWeatherList = heWeather.getHourlyWeatherList();
			BoundValueOperations<String, String> hourlyWeatherBVOper = redisTemplate.boundValueOps("info.weather.hourlyWeather." + date + "." + cityId);
			hourlyWeatherBVOper.set(GlobalUtils.parseOject2Json(hourlyWeatherList));
			for (HourlyWeather hourlyWeather : hourlyWeatherList) {
				BoundValueOperations<String, String> hourlyWeatherBHOper = redisTemplate.boundValueOps("info.weather.hourlyWeather." + GlobalUtils.parseDate("yyyy-MM-dd-HH:mm", hourlyWeather.getDate()) + "." + cityId);
				hourlyWeatherBHOper.set(GlobalUtils.parseOject2Json(hourlyWeather));
			}
			
			long timeout = Long.valueOf(GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "timeout.weather.city"));
			// 给超时标志的value值赋为城市id，无任何意义
			bvOper.set(cityId, timeout, TimeUnit.SECONDS);
		}
	}*/

	/*private String queryCityByCache(String cityId) {
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
	}*/
	
	/*private String queryRealTimeWeatherByCache(String cityId) {
		BoundValueOperations<String, String> realTimeWeatherBVOper = redisTemplate.boundValueOps("info.weather.realTimeWeather." + cityId);
		String value = realTimeWeatherBVOper.get();
		
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			RealTimeWeather realTimeWeather = heWeather.getRealTimeWeather();
			value = GlobalUtils.parseOject2Json(realTimeWeather);
			realTimeWeatherBVOper.set(value);
		}
		return value;
	}*/
	
	/*private String queryAirQualityByCache(String cityId) {
		BoundValueOperations<String, String> airQualityBVOper = redisTemplate.boundValueOps("info.weather.airQuality." + cityId);
		String value = airQualityBVOper.get();
		
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			AirQuality airQuality = heWeather.getAirQuality();
			value = GlobalUtils.parseOject2Json(airQuality);
			airQualityBVOper.set(value);
		}
		return value;
	}*/
	
	/*private String querySuggestionByCache(String cityId) {
		BoundValueOperations<String, String> suggestionBVOper = redisTemplate.boundValueOps("info.weather.suggestion." + cityId);
		String value = suggestionBVOper.get();
		
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			Suggestion suggestion = heWeather.getSuggestion();
			value = GlobalUtils.parseOject2Json(suggestion);
			suggestionBVOper.set(value);
		}
		return value;
	}*/
	
	/*private String queryFutureWeatherByCache(String cityId) {
		BoundValueOperations<String, String> dailyWeatherBVOper = redisTemplate.boundValueOps("info.weather.dailyWeather." + cityId);
		String value = dailyWeatherBVOper.get();
		
		if (value == null) {
			HeWeather heWeather = this.getObjectFromHttp(cityId);
			
			List<DailyWeather> dailyWeatherList = heWeather.getDailyWeatherList();
			value = GlobalUtils.parseOject2Json(dailyWeatherList);
			dailyWeatherBVOper.set(value);
		}
		return value;
	}*/
	
	private String queryHistoryWeatherByCache(String cityId, Date beginDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		for (int i = 1; i <= days; i++) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			String value = weatherDao.queryDailyWeatherByCache(cityId, GlobalUtils.parseDate("yyyy-MM-dd", calendar.getTime()));
			if (value == null)
				continue;
			sb.append(value + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}
	
	private String queryDailyTemperByCache(String cityId, Date beginDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		for (int i = 1; i <= days; i++) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			String value = weatherDao.queryDailyTemperByCache(cityId, GlobalUtils.parseDate("yyyy-MM-dd", calendar.getTime()));
			if (value == null)
				continue;
			sb.append(value + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		return sb.toString();
	}

}