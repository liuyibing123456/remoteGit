package org.whocares.weather.template;

import java.util.Map;

import org.junit.Test;
import org.whocares.weather.util.GlobalUtils;

public class AbstractProcessTemplateTest {
	
	@Test
	public void testDealResMessage() {
		String responseStr = "{\"errNum\":0,\"errMsg\":\"success\",\"retData\":[{\"province_cn\":\"\u5317\u4eac\",\"district_cn\":\"\u5317\u4eac\",\"name_cn\":\"\u6d77\u6dc0\",\"name_en\":\"haidian\",\"area_id\":\"101010200\"}]}";
		@SuppressWarnings("unchecked")
		Map<String, Object> resPackage = GlobalUtils.parseResponseJson(responseStr, Map.class);
		
		String successCode = GlobalUtils.getPropVal(GlobalUtils.ERRORCODE_PROPS, "error.info.success");
		System.out.println("successCode : " + successCode);
		if (!successCode.equals(resPackage.get("errNum"))) {
			System.out.println("log...");
		}
		System.out.println(resPackage.get("errNum"));
	}
}
