package org.whocares.weather.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class WeatherControllerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherControllerTest.class);
	
	private String getRemoteAsString(String url) {
		try {
			Resource resource = new UrlResource(url);
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			return sb.toString();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	@Test
	public void testQueryCity() {
		String url = "http://localhost:8080/weather/weather/CN101010100/city.do";
		String retMsg = this.getRemoteAsString(url);
		LOGGER.debug("==============" + retMsg);
	}

}
