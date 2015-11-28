package org.whocares.weather.app.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whocares.weather.app.service.IWeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	private IWeatherService weatherService;

	@RequestMapping(value = "/queryCity.do")
	public @ResponseBody String queryCity(String cityId) {
		return weatherService.queryCityInfo(cityId);
	}

	@RequestMapping(value = "/queryAqi.do")
	public @ResponseBody String queryAirQuality(String cityId) {
		return weatherService.queryAirQualityInfo(cityId);
	}

	@RequestMapping(value = "/queryRealTimeInfo.do")
	public @ResponseBody String queryRealTimeWeather(String cityId) {
		return weatherService.queryRealTimeWeatherInfo(cityId);
	}

	@RequestMapping(value = "/queryHourlyInfo.do")
	public @ResponseBody String queryHourlyWeather(String cityId) {
		return weatherService.queryHourlyWeatherInfo(cityId);
	}

	@RequestMapping(value = "/queryFutureInfo.do")
	public @ResponseBody String queryFutureWeather(String cityId) {
		return weatherService.queryFutureWeatherInfo(cityId);
	}

	@RequestMapping(value = "/queryHistoryInfo.do")
	public @ResponseBody String queryHistoryWeather(String cityId, 
			@DateTimeFormat(pattern = "yyyyMMdd") Date beginDate, int days) {
		return weatherService.queryHistoryWeatherInfo(cityId, beginDate, days);
	}
	
	@RequestMapping(value = "/queryDailyTemperInfo.do")
	public @ResponseBody String queryDailyTemper(String cityId, 
			@DateTimeFormat(pattern = "yyyyMMdd") Date beginDate, int days) {
		return weatherService.queryDailyTemperInfo(cityId, beginDate, days);
	}

	@RequestMapping(value = "/querySuggestion.do")
	public @ResponseBody String querySuggestion(String cityId) {
		return weatherService.querySuggestionInfo(cityId);
	}
}
