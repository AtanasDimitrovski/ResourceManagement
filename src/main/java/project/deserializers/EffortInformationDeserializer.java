package project.deserializers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import project.model.EffortInformation;
import project.model.EffortInformation.Role;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class EffortInformationDeserializer extends JsonDeserializer<EffortInformation>{

	@Override
	public EffortInformation deserialize(JsonParser jsonParser,
			DeserializationContext deserializationContext) throws IOException,
			JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode jsonNode = oc.readTree(jsonParser);
		EffortInformation effortInformation = new EffortInformation();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (jsonNode.has("fromDate")){
			try {
				effortInformation.setFromDate(format.parse(jsonNode.get("fromDate").asText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if (jsonNode.has("toDate")){
			try {
				effortInformation.setToDate(format.parse(jsonNode.get("toDate").asText()));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (jsonNode.has("percent")){
			effortInformation.setPercent(jsonNode.get("percent").asInt());
		}
		if (jsonNode.has("role")){
			effortInformation.setRole(Role.valueOf(jsonNode.get("role").asText()));
		}
		return effortInformation;
	}
	
	
}
