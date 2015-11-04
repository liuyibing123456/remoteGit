package org.whocares.weather.dao.template;

import java.util.List;

import org.springframework.data.redis.core.BoundValueOperations;
import org.whocares.weather.entity.DailyWeather;
import org.whocares.weather.entity.HeWeather;
import org.whocares.weather.util.GlobalUtils;

public class DailyTemperProccessObjTemplate extends CommonProcessObjTemplate {

	@Override
	protected void cacheDailyInfo(HeWeather heWeather, String cityId) throws RuntimeException {
		super.cacheDailyInfo(heWeather, cityId);
		this.cacheDailyTemperInfo(heWeather, cityId);
	}
	
	protected void cacheDailyTemperInfo(HeWeather heWeather, String cityId) throws RuntimeException {
		BoundValueOperations<String, String> futureWeatherBVOper = this.getRedisTemplate().boundValueOps("info.weather.dailyWeather." + cityId);
		
		@SuppressWarnings("unchecked")
		List<DailyWeather> dailyWeatherList = GlobalUtils.parseResponseJson(futureWeatherBVOper.get(), List.class);
		
		for (DailyWeather dailyWeather : dailyWeatherList) {
			BoundValueOperations<String, String> dailyWeatherTemperBVOper = this.getRedisTemplate().boundValueOps("info.weather.dailyWeather.temper" + GlobalUtils.parseDate("yyyy-MM-dd", dailyWeather.getDate()) + "." + cityId);
			dailyWeatherTemperBVOper.set(GlobalUtils.parseOject2Json(dailyWeather.getTemper()));
		}
	}
}
