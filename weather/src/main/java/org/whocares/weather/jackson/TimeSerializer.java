package org.whocares.weather.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TimeSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		gen.writeString(formatter.format(date));
	}

}
