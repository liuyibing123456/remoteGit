package org.whocares.weather.service.impl;

import org.whocares.weather.service.IWeatherInfoService;
import org.whocares.weather.template.AbstractProcessTemplate;
import org.whocares.weather.template.ProcessTemplate;

public class WeatherInfoServiceImpl implements IWeatherInfoService {

	@Override
	public String queryHistory(String cityId) {
		return null;
	}

	@Override
	public String queryByCitySpell(String spell) {
		return null;
	}

	@Override
	public String queryByCityName(String cityNameCN) {
		return null;
	}

	@Override
	public String queryByCityId(String cityId) {
		AbstractProcessTemplate proccessTemplate = new ProcessTemplate();
		return proccessTemplate.template("url.weather.city_id", "cityid", cityId);
	}

}
