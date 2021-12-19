package com.revature.dkwte.service;

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

}
