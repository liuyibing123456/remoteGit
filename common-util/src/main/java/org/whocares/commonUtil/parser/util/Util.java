package org.whocares.commonUtil.parser.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Util {

	private static final XmlMapper xmlMapper = new XmlMapper();
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static Map<String, String> parseXMLToMap(byte[] src) {
		return parseXMLToMap(src, String.class);
	}
	
	public static <T> Map<String, T> parseXMLToMap(byte[] src, Class<T> valueType) {
		return parseXMLToMap(src, String.class, valueType);
	}
	
	public static <K, V> Map<K, V> parseXMLToMap(byte[] src, Class<K> keyType, Class<V> valueType) {
		JavaType javaType = xmlMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
		try {
			return xmlMapper.readValue(src, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, String> parseXMLToMap(InputStream src) {
		return parseXMLToMap(src, String.class);
	}
	
	public static <T> Map<String, T> parseXMLToMap(InputStream src, Class<T> valueType) {
		return parseXMLToMap(src, String.class, valueType);
	}
	
	public static <K, V> Map<K, V> parseXMLToMap(InputStream src, Class<K> keyType, Class<V> valueType) {
		JavaType javaType = xmlMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
		try {
			return xmlMapper.readValue(src, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <K, V> Map<K, V> parseJSONToMap(byte[] src, Class<K> keyType, Class<V> valueType) {
		JavaType javaType = objectMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType);
		try {
			return objectMapper.readValue(src, javaType);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
