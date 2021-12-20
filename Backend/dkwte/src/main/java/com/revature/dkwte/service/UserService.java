package com.revature.dkwte.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.revature.dkwte.controller.AuthenticationController;
import com.revature.dkwte.dao.UserDao;
import com.revature.dkwte.exception.InvalidLoginException;
import com.revature.dkwte.model.User;

@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private UserDao userDao;

	public User getUserByEmailAndPassword(String email, String password) throws InvalidLoginException {
		logger.info("UserService.getUserByEmailAndPassword() invoked");

		try {
			User user = this.userDao.getUserByEmailAndPassword(email, password);
			return user;

		} catch (DataAccessException e) {
			throw new InvalidLoginException("Username and/or password is invorrect");
		}

	}

	public User signUp(User user) {
		logger.info("UserService.signUp() invoked");

//		String algorithm = "SHA-256";
//		String hashedPassword = HasUtil.hashPassword(user.getPassword().trim(), algorithm);

		user.setFirstName(user.getFirstName().trim());
		user.setLastName(user.getLastName().trim());
		user.setPhoneNumber(user.getPhoneNumber().trim());
		user.setEmail(user.getEmail().trim().toLowerCase());
		user.setPassword(user.getPassword().trim());
		user.setUserRole("Member");

		User addedUser = this.userDao.signUp(user);

		return addedUser;
	}

	public List<User> getUserByEmail(String email) {
		logger.info("UserService.getUserByEmail() invoked");

		List<User> users = this.userDao.getUserByEmail(email);

		return users;
	}

}
