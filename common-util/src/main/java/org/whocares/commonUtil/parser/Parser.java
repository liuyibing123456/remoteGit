package org.whocares.commonUtil.parser;

import java.util.Map;

public interface Parser {
	
	public abstract <T> Map<String, T> parse(byte[] src);

}
