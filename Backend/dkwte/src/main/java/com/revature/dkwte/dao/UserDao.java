package com.revature.dkwte.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.revature.dkwte.model.User;
import com.revature.dkwte.utility.ValidateUtil;

@Repository
public class UserDao {

	private static Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public User getUserByEmailAndPassword(String email, String password) {
		logger.info("UserDao.getUserByEmailAndPassword() invoked");

		logger.debug("email and password {}{}", email, password);

		User user = entityManager
				.createQuery("FROM User u WHERE u.email = :email AND u.password = :password", User.class)
				.setParameter("email", email).setParameter("password", password).getSingleResult();

		/*-
		 * getSingleResult() method will throw NoSuchResultException 
		 * if there is no User matching that particular email and password
		 * 
		 * However, Spring will take that exception and translate it into 
		 * DataAccessException and throw that exception instead.
		 */
		return user;
	}

	@Transactional
	public User signUp(User user) {
		logger.info("UserDao.signUp() invoked");

		User signUpUser = new User();

		signUpUser.setFirstName(user.getFirstName());
		signUpUser.setLastName(user.getLastName());
		signUpUser.setPhoneNumber(user.getPhoneNumber());
		signUpUser.setEmail(user.getEmail());
		signUpUser.setPassword(user.getPassword());
		signUpUser.setUserRole(user.getUserRole());

		entityManager.persist(signUpUser);

		return signUpUser;
	}

	@Transactional
	public List<User> getUserByEmail(String email) {
		logger.info("UserDao.getUserByEmail() invoked");

		List<User> users = entityManager.createQuery("FROM User u", User.class).getResultList();

		logger.info("users {}", users);

		return users;
	}

}
