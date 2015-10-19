package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.model.User;
import project.service.EmployeeService;
import project.service.UserService;

@Controller
public class MainController {

	  @RequestMapping("/")
	  @ResponseBody
	    public String index() {
	        return "Greetings from Spring Boot!";
	    }
	
}	
