package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.controller.UserController;
import project.model.User;
import project.model.User.Role;

@Service
public class UserService {
	
	@Autowired
	private UserController userController;
	
	public boolean create(String username, String password){
		return userController.create(username, password);
	}
	
	public boolean delete(long id){
		return userController.delete(id);
	}
	
	public boolean changePassword(long id, String password){
		return userController.changePassword(id, password);
	}
	
	public boolean changeRole(long id, Role role){
		return userController.changeRole(id, role);
	}
	
	public boolean changeUsername(long id, String username){
		return userController.changeUsername(id, username);
	}
	
	public User get(long id){
		return userController.get(id);
	}
	
	public List<User> getAll(){
		return userController.getAll();
	}
	
}





