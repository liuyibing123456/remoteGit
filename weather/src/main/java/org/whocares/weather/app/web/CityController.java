package org.whocares.weather.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.whocares.weather.app.service.ICityService;

@Controller
@RequestMapping("/city")
public class CityController {

	@Autowired
	private ICityService cityService;

	@RequestMapping(value = "/queryProvinces.do")
	public @ResponseBody String queryProvinces() {
		return cityService.queryProvinceList();
	}
	
	@RequestMapping(value = "/queryCities.do")
	public @ResponseBody String queryCities(String province) {
		return cityService.queryCityList(province);
	}
	
	@RequestMapping(value = "/queryDistricts.do")
	public @ResponseBody String queryDistricts(String province, String city) {
		return cityService.queryDistrictList(province, city);
	}

}
