package project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import project.dao.UserDao;
import project.model.User;

public class UserDetailService implements UserDetailsService {
	
	
	@Autowired
	private UserDao userDao;
	
	protected UserDao getRepo(){
		return userDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = getRepo().findByUsername(username);
		return new project.security.UserDetails(user);
	}
	
	
	
}
