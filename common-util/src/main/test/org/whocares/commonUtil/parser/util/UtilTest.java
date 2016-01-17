package org.whocares.commonUtil.parser.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class UtilTest {
	
	@Test
	public void testParseXMLToMap() {
		InputStream is = null;
		try {
			byte[] b = new byte[1024];
			is = new FileInputStream("E:/Git/remoteGit/common-util/src/main/resources/channel.xml");
			try {
				is.read(b);
				//Map<String, Object> map = Util.parseXMLToMap(b, Object.class);
				XmlMapper xmlMapper = new XmlMapper();
				System.out.println(xmlMapper.readValue(is, Map.class));
				//System.out.println(map.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
