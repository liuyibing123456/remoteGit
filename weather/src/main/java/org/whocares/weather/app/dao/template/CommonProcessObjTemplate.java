package org.whocares.weather.app.dao.template;

import java.util.List;

import org.springframework.data.redis.core.BoundValueOperations;
import org.whocares.weather.app.entity.weather.AirQuality;
import org.whocares.weather.app.entity.weather.City;
import org.whocares.weather.app.entity.weather.DailyWeather;
import org.whocares.weather.app.entity.weather.HeWeather;
import org.whocares.weather.app.entity.weather.HourlyWeather;
import org.whocares.weather.app.entity.weather.RealTimeWeather;
import org.whocares.weather.app.entity.weather.Suggestion;
import org.whocares.weather.util.GlobalUtils;

public class CommonProcessObjTemplate extends AbstractProccessObjTemplate {
	
	private final String CURRENT_DATE = GlobalUtils.getCurrentDate();
	
	protected String getCurrentDate() {
		return CURRENT_DATE;
	}

	@Override
	protected void cacheCityInfo(HeWeather heWeather, String cityId)
			throws RuntimeException {
		BoundValueOperations<String, String> cityBVOper = this.getRedisTemplate().boundValueOps("info.weather.city." + CURRENT_DATE + "." + cityId);
		City city = heWeather.getCity();
		cityBVOper.set(GlobalUtils.parseOject2Json(city));
	}

	@Override
	protected void cacheRealTimeInfo(HeWeather heWeather, String cityId)
			throws RuntimeException {
		BoundValueOperations<String, String> realTimeWeatherBVOper = this.getRedisTemplate().boundValueOps("info.weather.realTimeWeather." + CURRENT_DATE + "." + cityId);
		RealTimeWeather realTimeWeather = heWeather.getRealTimeWeather();
		realTimeWeatherBVOper.set(GlobalUtils.parseOject2Json(realTimeWeather));		
	}

	@Override
	protected void cacheDailyInfo(HeWeather heWeather, String cityId)
			throws RuntimeException {
		List<DailyWeather> dailyWeatherList = heWeather.getDailyWeatherList();
		
		BoundValueOperations<String, String> futureWeatherBVOper = this.getRedisTemplate().boundValueOps("info.weather.dailyWeather." + cityId);
		futureWeatherBVOper.set(GlobalUtils.parseOject2Json(dailyWeatherList));
		
		for (DailyWeather dailyWeather : dailyWeatherList) {
			BoundValueOperations<String, String> dailyWeatherBVOper = this.getRedisTemplate().boundValueOps("info.weather.dailyWeather." + GlobalUtils.parseDate("yyyy-MM-dd", dailyWeather.getDate()) + "." + cityId);
			dailyWeatherBVOper.set(GlobalUtils.parseOject2Json(dailyWeather));
		}
	}

	@Override
	protected void cacheHourlyInfo(HeWeather heWeather, String cityId)
			throws RuntimeException {
		List<HourlyWeather> hourlyWeatherList = heWeather.getHourlyWeatherList();
		BoundValueOperations<String, String> hourlyWeatherBVOper = this.getRedisTemplate().boundValueOps("info.weather.hourlyWeather." + CURRENT_DATE + "." + cityId);
		hourlyWeatherBVOper.set(GlobalUtils.parseOject2Json(hourlyWeatherList));		
	}

	@Override
	protected void cacheAirQualityInfo(HeWeather heWeather, String cityId)
			throws RuntimeException {
		BoundValueOperations<String, String> airQualityBVOper = this.getRedisTemplate().boundValueOps("info.weather.airQuality." +CURRENT_DATE + "." + cityId);
		AirQuality airQuality = heWeather.getAirQuality();
		airQualityBVOper.set(GlobalUtils.parseOject2Json(airQuality));		
	}

	@Override
	protected void cacheSuggestionInfo(HeWeather heWeather, String cityId)
			throws RuntimeException {
		BoundValueOperations<String, String> suggestionBVOper = this.getRedisTemplate().boundValueOps("info.weather.suggestion." + CURRENT_DATE + "." + cityId);
		Suggestion suggestion = heWeather.getSuggestion();
		suggestionBVOper.set(GlobalUtils.parseOject2Json(suggestion));		
	}

}
