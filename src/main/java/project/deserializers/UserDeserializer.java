package project.deserializers;

import java.io.IOException;

import project.model.Employee;
import project.model.User;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class UserDeserializer extends JsonDeserializer<User> {

	@Override
	public User deserialize(JsonParser jsonParser, DeserializationContext deserilazationContext)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		User user = new User();
		
		if(node.has("username"))
			user.setUsername(node.get("username").asText());
		if(node.has("password"))
			user.setPassword(node.get("password").asText());
		return user;
	}

}
