package com.revature.dkwte.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dkwte.dto.LoginDTO;
import com.revature.dkwte.exception.InvalidLoginException;
import com.revature.dkwte.model.User;
import com.revature.dkwte.service.UserService;
import com.revature.dkwte.utility.ValidateUtil;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class AuthenticationController {

	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private HttpServletRequest req; // request scoped bean

	@Autowired
	private HttpServletResponse res;

	// constants
	private static final String CURRENTUSER = "currentuser";

	@PostMapping(path = "/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO loginDto) {
		logger.info("AuthenticationController.login() invoked");

		try {
			ValidateUtil.verifyEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

			User user = this.userService.getUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

			HttpSession session = req.getSession();
			session.setAttribute(CURRENTUSER, user);

			/*
			 * SameSite=None requires Https to be enabled for the backend
			 */
//			String originalSetCookieHeader = res.getHeader("Set-Cookie");
//			String newCookieHeader = originalSetCookieHeader + "; SameSite=None; Secure";
//			res.setHeader("Set-Cookie", newCookieHeader);

			return ResponseEntity.status(200).body(user);

		} catch (InvalidLoginException e) {

			return ResponseEntity.status(400).body(e.getMessage());
		}
	}

	@GetMapping(path = "/loginstatus")
	public ResponseEntity<Object> logiinStatus() {
		logger.info("AuthenticationController.logiinStatus() invoked");
		
		User currentlyLoggedInUser = (User) req.getSession().getAttribute(CURRENTUSER);
		
		logger.info("currentlyLoggedInUser {}", currentlyLoggedInUser);

		if (currentlyLoggedInUser != null) {
			return ResponseEntity.status(200).body(currentlyLoggedInUser);
		}

		// currentlyLoggedInUser is null
		return ResponseEntity.status(401).body("Not logged in");
	}

	@PostMapping(path = "/logout")
	public ResponseEntity<String> logout() {
		logger.info("AuthenticationController.logout() invoked");

		req.getSession().invalidate(); // invalidate the session (logging out)

		return ResponseEntity.status(200).body("Sucessfully logged out");
	}
	
	@PostMapping(path = "/signup")
	public ResponseEntity<Object> signUp() {
		logger.info("AuthenticationController.signUp() invoked");
		
		ValidateUtil.verifySignUp(user);

		User user = userService.signUp(user);
		
		return ResponseEntity.status(200).body(user);
	}

}
