package project.rest_service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.security.TokenTransfer;
import project.security.TokenUtils;

@RestController
public class UserResource {
	
	private static final int TOKEN_DURATION = 30 * 24 * 60 * 60;
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;
	
	/**
	 * Authenticates a user and creates an authentication token.
	 * 
	 * @param username
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @return A transfer containing the authentication token.
	 */
	@RequestMapping(value = "/user/authenticate", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public TokenTransfer authenticate(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("rememberMe") boolean rememberMe,
			HttpServletRequest request, HttpServletResponse response) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		Authentication authentication = this.authManager
				.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null
		 * after authorization and password is needed for token generation
		 */
		UserDetails userDetails = this.userService.loadUserByUsername(username);
		Cookie cookie = new Cookie("token", TokenUtils.createToken(userDetails));
		if (rememberMe) {
			cookie.setMaxAge(TOKEN_DURATION);
		}
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		return new TokenTransfer(TokenUtils.createToken(userDetails));
	}
}
