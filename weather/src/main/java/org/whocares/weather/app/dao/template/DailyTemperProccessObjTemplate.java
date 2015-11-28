package org.whocares.weather.app.dao.template;

import java.util.List;

import org.springframework.data.redis.core.BoundValueOperations;
import org.whocares.weather.app.entity.weather.DailyWeather;
import org.whocares.weather.app.entity.weather.HeWeather;
import org.whocares.weather.util.GlobalUtils;

public class DailyTemperProccessObjTemplate extends CommonProcessObjTemplate {

	@Override
	protected void cacheDailyInfo(HeWeather heWeather, String cityId) throws RuntimeException {
		super.cacheDailyInfo(heWeather, cityId);
		this.cacheDailyTemperInfo(heWeather, cityId);
	}
	
	protected void cacheDailyTemperInfo(HeWeather heWeather, String cityId) throws RuntimeException {
		
		List<DailyWeather> dailyWeatherList = heWeather.getDailyWeatherList();
		
		for (DailyWeather dailyWeather : dailyWeatherList) {
			BoundValueOperations<String, String> dailyWeatherTemperBVOper = this.getRedisTemplate().boundValueOps("info.weather.dailyWeather.temper." + GlobalUtils.parseDate("yyyy-MM-dd", dailyWeather.getDate()) + "." + cityId);
			
			DailyWeather newInstance = new DailyWeather();
			newInstance.setTemper(dailyWeather.getTemper());
			newInstance.setDate(dailyWeather.getDate());
			
			dailyWeatherTemperBVOper.set(GlobalUtils.parseOject2Json(newInstance));
		}
	}
}
