package com.revature.dkwte.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.revature.dkwte.controller.AuthenticationController;
import com.revature.dkwte.dao.UserDao;
import com.revature.dkwte.exception.InvalidLoginException;
import com.revature.dkwte.model.User;
import com.revature.dkwte.utility.HashUtil;

@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private UserDao userDao;

	public User getUserByEmailAndPassword(String email, String password)
			throws InvalidLoginException, NoSuchAlgorithmException {
		logger.info("UserService.getUserByEmailAndPassword() invoked");

		User user = this.userDao.getUserByEmail(email);

		try {

			String algorithm = "SHA-256";
			String hashedInputPassword = HashUtil.hashInputPassword(password.trim(), algorithm);

			Boolean isCorrectPassword = hashedInputPassword.equals(user.getPassword());

			if (isCorrectPassword) {
				return user;
			} else {
				throw new InvalidLoginException("Incorrect username and/or password");
			}

//			User user = this.userDao.getUserByEmailAndPassword(email, password);

		} catch (DataAccessException e) {
			throw new InvalidLoginException("Username and/or password is invorrect");
		}

	}

	public User signUp(User user) throws NoSuchAlgorithmException {
		logger.info("UserService.signUp() invoked");

		String algorithm = "SHA-256";
		String hashedPassword = HashUtil.hashPassword(user.getPassword().trim(), algorithm);

		// capitalize first letter of user role
		String roleInput = user.getUserRole().trim();
		String userRole = roleInput.substring(0, 1).toUpperCase() + roleInput.substring(1);

		user.setFirstName(user.getFirstName().trim());
		user.setLastName(user.getLastName().trim());
		user.setPhoneNumber(user.getPhoneNumber().trim());
		user.setEmail(user.getEmail().trim().toLowerCase());
		user.setPassword(hashedPassword);
		user.setUserRole(userRole);

		User addedUser = this.userDao.signUp(user);

		return addedUser;
	}

	public User getUserByEmail(String email) {
		logger.info("UserService.getUserByEmail() invoked");

		User user = this.userDao.getUserByEmail(email);

		return user;
	}

}
