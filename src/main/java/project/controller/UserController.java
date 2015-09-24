package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.dao.UserDao;
import project.model.User;
import project.model.User.Role;

@Controller
public class UserController {
	
	
	@Autowired
	private UserDao userDao;

	public boolean create(String username){
				
		try {
			
			User user = new User();
			user.setUsername(username);
			user.setPassword("pasvord");
			user.setRole(User.Role.ROLE_USER);
			userDao.save(user);
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean delete(long id){
				
		try {
			User user = userDao.getOne(id);
			short valid = 0;
			user.setValid(valid);
			userDao.saveAndFlush(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean changeUsername(long id, String username){
		try {
			User user = userDao.getOne(id);
			user.setUsername(username);
			userDao.saveAndFlush(user);
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean changePassword(long id, String password){
		try {
			User user = userDao.getOne(id);
			user.setPassword(password);
			userDao.saveAndFlush(user);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public boolean changeRole(long id, Role role){
		try {
			User user = userDao.getOne(id);
			user.setRole(role);
			userDao.saveAndFlush(user);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public User read(long id){	
		try {
			return userDao.getOne(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}
