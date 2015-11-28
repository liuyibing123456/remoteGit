package org.whocares.weather.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.whocares.weather.util.GlobalUtils;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GlobalUtilsTest {

	@Test
	public void testGetPropVal() {
		assertEquals(
				GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "apikey"),
				"1e9c449b4bf78ee4bd95fc59a5d5b99c");

		assertNull(GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS,
				"url.citylist.null"));
	}
	
	@Test
	public void testHttpConnection() {
		String url = GlobalUtils.getPropVal(GlobalUtils.CONFIG_PROPS, "url.weather.city_id");
		Map<String, String> heads = new HashMap<String, String>();
		heads.put("apikey", "1e9c449b4bf78ee4bd95fc59a5d5b99c");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("cityid", "101010100");
		String response = GlobalUtils.httpConnection(url, heads, params);
		System.out.println(response);
	}
	
	@Test
	public void testGetJson() throws Exception {
		Resource resource = new ClassPathResource("response.json");
		InputStream is = resource.getInputStream();
		BufferedReader bf=new BufferedReader(new InputStreamReader(is,"UTF-8"));
	     StringBuffer buffer=new StringBuffer();
	     String line="";
	     while((line=bf.readLine())!=null){
	         buffer.append(line);
	     }
	     System.out.println(buffer.toString());
//		System.out.println(GlobalUtils.getJson(buffer.toString(), "pm10"));
	}
	@Test
	public void testParseResponseJson() throws IOException {
		String jsonStr = "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}";
		JsonGenerator jsonGenerator = new JsonFactory().createJsonGenerator(new File("cityCode.json"), JsonEncoding.UTF8);
		ObjectMapper mapper = new ObjectMapper();
	}
	
	@Test
	public void test() {
		char a = 27;
		String str = "2146101103存放境外同业活期款项*POST";
		System.out.println(str);
		System.out.println(str.replace(a, 'A'));
	}

}
