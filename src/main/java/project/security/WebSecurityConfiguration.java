package project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import project.dao.UserDao;
import project.model.User;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
	
	@Autowired
	UserDao userDao;

	  @Override
	  public void init(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService());
	  }

	  @Bean
	  UserDetailsService userDetailsService() {
	    return new UserDetailsService() {

	      @Override
	      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userDao.findByUsername(username);
	        if(user != null) {
	        	return new project.security.UserDetails(user);
	        } else {
	          throw new UsernameNotFoundException("could not find the user '"
	                  + username + "'");
	        }
	      }
	      
	    };
	  }
	
}
