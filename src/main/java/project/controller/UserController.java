package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.dao.UserDao;
import project.model.User;
import project.model.User.Role;

@Controller
public class UserController extends BaseController<User, JpaRepository<User,Long>> {
	
	
	@Autowired
	private UserDao userDao;
	
	@Override
	protected JpaRepository<User, Long> getDao() {
		return userDao;
	}
	
	public User findByUsername(String username){
		try {
			return userDao.findByUsername(username);
		} catch (Exception e) {
			return null;
		}
	}
	
	public User findByEmployeeId(long id){
		return userDao.findByEmployeeId(id).get(0);
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

}
