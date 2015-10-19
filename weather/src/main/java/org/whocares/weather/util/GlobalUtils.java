package org.whocares.weather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class GlobalUtils {
	
	/**
	 * config.properties �ļ���
	 */
	public static final String CONFIG_PROPS = "config.properties";
	
	/**
	 * errorCode.properties �ļ���
	 */
	public static final String ERRORCODE_PROPS = "errorCode.properties";
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * ����properties�ļ�����key��ȡ��Ӧ��value��Ĭ������·����Ѱ���ļ���<br>
	 * �������ļ�ʧ�ܻ��Ҳ�����Ӧkeyʱ����null��
	 * 
	 * @param propName <br>properties�ļ�
	 * @param key <br>properties��key
	 * @return <br> key��Ӧ��value
	 */
	public static final String getPropVal(String propName, String key) {
		try {
			Resource resource = new ClassPathResource(propName);
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			return props.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
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
}
