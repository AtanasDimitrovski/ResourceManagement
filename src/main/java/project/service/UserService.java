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
	
	/**
	 * Creates new user
	 * @param user User
	 * @return Created user
	 */
	public User create(User user){
		short valid = 1;
		user.setValid(valid);
		return userController.save(user);
	}
	
	/**
	 * Deletes user with user id
	 * @param id user id
	 */
	public void delete(long id){
		User user = userController.findOne(id);
		short valid = 0;
		user.setValid(valid);
	}
	
	/**
	 * Gets user with user id
	 * @param id user id
	 * @return User
	 */
	public User get(long id){
		return userController.findOne(id);
	}
	
	/**
	 * Gets all users 
	 * @return List of users
	 */
	public List<User> getAll(){
		return userController.findAll();
	}
	
	public User findByUsername(String username){
		return userController.findByUsername(username);
	}
	
}





