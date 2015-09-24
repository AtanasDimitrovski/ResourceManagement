package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.controller.UserController;
import project.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserController userController;
	
	public User getUser(long id){
		return userController.read(id);
	}
	
}
