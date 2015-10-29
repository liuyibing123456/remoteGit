package org.whocares.weather.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.whocares.weather.service.IWeatherService;

@Controller
@RequestMapping("/weather/{cityId}")
public class WeatherController {
	
	@Autowired
	private IWeatherService weatherService;
	
	@RequestMapping(value = "/city", produces = "application/json")
	public String queryCity(@PathVariable String cityId) {
		return weatherService.queryCityInfo(cityId);
	}
	
	@RequestMapping(value = "/aqi", produces = "application/json")
	public String queryAirQuality(@PathVariable String cityId) {
		return weatherService.queryAirQualityInfo(cityId);
	}
	
	@RequestMapping(value = "/realTimeInfo", produces = "application/json")
	public String queryRealTimeWeather(@PathVariable String cityId) {
		return weatherService.queryRealTimeWeatherInfo(cityId);
	}
	
	@RequestMapping(value = "/hourlyInfo", produces = "application/json")
	public String queryHourlyWeather(@PathVariable String cityId) {
		return weatherService.queryHourlyWeatherInfo(cityId);
	}
	
	@RequestMapping(value = "/futureInfo", produces = "application/json")
	public String queryFutureWeather(@PathVariable String cityId) {
		return weatherService.queryFutureWeatherInfo(cityId);
	}
	
	@RequestMapping(value = "/futureInfo/{beginDate}/{days}", produces = "application/json")
	public String queryFutureWeather(@PathVariable String cityId, @PathVariable Date beginDate, @PathVariable int days) {
		return weatherService.queryHistoryWeatherInfo(cityId, beginDate, days);
	}
	
	@RequestMapping(value = "/suggestion", produces = "application/json")
	public String querySuggestion(@PathVariable String cityId) {
		return weatherService.querySuggestionInfo(cityId);
	}
}
