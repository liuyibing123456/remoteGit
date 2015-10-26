package org.whocares.weather.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class GlobalUtils {
	
	/**
	 * config.properties 文件名
	 */
	public static final String CONFIG_PROPS = "config.properties";
	
	/**
	 * errorCode.properties 文件名
	 */
	public static final String ERRORCODE_PROPS = "errorCode.properties";
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalUtils.class);
	
	/**
	 * 根据properties文件及其key获取对应的value，默认在类路径下寻找文件。<br>
	 * 当加载文件失败或找不到相应key时返回null。
	 * 
	 * @param propName <br>properties文件
	 * @param key <br>properties的key
	 * @return <br> key对应的value
	 */
	public static final String getPropVal(String propName, String key) {
		String value = null;
		try {
			Resource resource = new ClassPathResource(propName);
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			value = props.getProperty(key);
			
			LOGGER.info("get value [ {} ] through property [ {} ] in file [ {} ]", new Object[]{value, key, propName});
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
		return value;
	}
	
	/**
	 * 
	 * @param url
	 * @param heads
	 * @param params
	 */
	public static String httpConnection(String url, Map<String, String> heads, Map<String, String> params) {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET, "utf-8");
		GetMethod getMethod = new GetMethod(url);
		
		if (heads != null) {
			for (String key : heads.keySet()) {
				getMethod.addRequestHeader(key, heads.get(key));
			}
		}
		
		if (params != null) {
			// map convert to array
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				list.add(new NameValuePair(key, params.get(key)));
			}
			getMethod.setQueryString(list.toArray(new NameValuePair[1]));
		}
		
		try {
			int stateCode = httpClient.executeMethod(getMethod);
		    return stateCode == HttpStatus.SC_OK ? getMethod.getResponseBodyAsString() : null;
		} catch (HttpException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			getMethod.releaseConnection();
		}
	}
	
	
	public static <T> T parseResponseJson(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String parseOject2Json(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}