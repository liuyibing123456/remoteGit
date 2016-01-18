package org.whocares.commonUtil.parser.util;

import java.io.FileInputStream;
import java.util.Map;

import org.junit.Test;

public class UtilTest {

	@Test
	public void testParseXMLToMap() throws Exception {
		Map<String, Object> map = Util.parseXMLToMap(new FileInputStream("E:/git/remoteGit/common-util/src/main/resources/channel.xml"), Object.class);
		System.out.println(map.toString());
	}
}
