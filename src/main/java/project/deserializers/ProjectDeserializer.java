package project.deserializers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import project.model.Project;
import project.service.EmployeeService;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class ProjectDeserializer extends JsonDeserializer<Project> {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public Project deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode jsonNode = oc.readTree(jsonParser);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Project project = new Project();
		if(jsonNode.has("name"))
			project.setName(jsonNode.get("name").asText());
		if(jsonNode.has("description"))
			project.setDescription(jsonNode.get("description").asText());
		if(jsonNode.has("fromDate")){
			try {
				project.setFromDate(format.parse(jsonNode.get("fromDate").asText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(jsonNode.has("toDate")){
			try {
				project.setToDate(format.parse(jsonNode.get("toDate").asText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}if(jsonNode.has("status"))
			project.setStatus(jsonNode.get("status").asText());	
		if (jsonNode.has("managerId"))
			project.setManager(employeeService.getEmployee(jsonNode.get("managerId").asLong()));
		return project;
	}
	
	
}
