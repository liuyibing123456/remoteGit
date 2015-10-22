package org.whocares.weather.jackson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class TimeDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
		try {
			return formatter.parse(jp.getText());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
