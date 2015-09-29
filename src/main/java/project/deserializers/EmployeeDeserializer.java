package project.deserializers;

import java.io.IOException;

import project.model.Employee;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class EmployeeDeserializer extends JsonDeserializer<Employee> {

	@Override
	public Employee deserialize(JsonParser jsonParser, DeserializationContext deserilazationContext)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		Employee emp = new Employee();
		
		if(node.has("name"))
			emp.setName(node.get("name").asText());
		if(node.has("lastName"))
			emp.setLastName(node.get("lastName").asText());
		if(node.has("jobDescription"))
			emp.setJobDescription(node.get("jobDescription").asText());
		return emp;
	}

}
