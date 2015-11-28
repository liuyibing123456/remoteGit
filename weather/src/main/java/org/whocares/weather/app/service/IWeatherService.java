package org.whocares.weather.app.service;

import java.util.Date;

public interface IWeatherService {
	
	String queryCityInfo(String cityId);

	String queryRealTimeWeatherInfo(String cityId);

	String queryAirQualityInfo(String cityId);

	String querySuggestionInfo(String cityId);

	String queryFutureWeatherInfo(String cityId);

	String queryHistoryWeatherInfo(String cityId, Date beginDate, int days);

	String queryHourlyWeatherInfo(String cityId);
	
	String queryDailyTemperInfo(String cityId, Date beginDate, int days);
}