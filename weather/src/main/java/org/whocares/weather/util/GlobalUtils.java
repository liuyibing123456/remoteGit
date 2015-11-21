package org.whocares.weather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GlobalUtils {
	
	/**
	 * config.properties 文件名
	 */
	public static final String CONFIG_PROPS = "config.properties";
	
	/**
	 * errorCode.properties 文件名
	 */
	public static final String ERRORCODE_PROPS = "errorCode.properties";
	
	public static final String CITYCODE_JSON = "cityCode.json";
	
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
	public static String httpConnection(String url, Map<String, String> heads, Map<String, String> params) throws NullPointerException, RuntimeException {
		if(url == null) {
			throw new NullPointerException("param [ url ] is null");
		}
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_URI_CHARSET, "utf-8");
		GetMethod getMethod = new GetMethod(url);
		
		if (heads != null && heads.size() != 0) {
			for (String key : heads.keySet()) {
				getMethod.addRequestHeader(key, heads.get(key));
			}
		}
		
		if (params != null && params.size() != 0) {
			// map convert to array
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				list.add(new NameValuePair(key, params.get(key)));
			}
			getMethod.setQueryString(list.toArray(new NameValuePair[1]));
		}
		
		try {
			int stateCode = httpClient.executeMethod(getMethod);
			LOGGER.info("HttpStatus : " + stateCode);
		    if (stateCode != HttpStatus.SC_OK) {
		    	throw new RuntimeException("stateCode : " + stateCode);
		    }
		    return getMethod.getResponseBodyAsString();
		} catch (HttpException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			getMethod.releaseConnection();
		}
	}
	
	
	public static <T> T parseResponseJson(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 
	 * @param jsonStr 需要解析的json字符串
	 * @param beanClass 集合泛型类型
	 * @return List<T> 集合
	 */
	public static <T> List<T> parseResponseListJson(String jsonStr, Class<T> beanClass) {
		JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, beanClass);
		
		try {
			return objectMapper.readValue(jsonStr, javaType);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static String parseOject2Json(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static String readFileAsString(String classpath) {
		try {
			Resource resource = new ClassPathResource(classpath);
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			return sb.toString();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
	}
	
	public static String parseDate(String dateFormatter, Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatter);
		return formatter.format(date);
	}
	
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(new Date());
	}
	
}