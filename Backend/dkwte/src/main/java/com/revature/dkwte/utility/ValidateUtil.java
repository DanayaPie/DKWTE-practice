package com.revature.dkwte.utility;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.dkwte.exception.InvalidLoginException;
import com.revature.dkwte.exception.InvalidParameterException;
import com.revature.dkwte.model.User;
import com.revature.dkwte.service.UserService;

public class ValidateUtil {

	Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

	@Autowired
	UserService userService;

	static List<String> userRoleList = Arrays.asList("member", "admin");

	public void verifyEmailAndPassword(String email, String password) throws InvalidLoginException {
		logger.info("ValidteUtil.verifyEmailAndPassword() invoked");

		boolean blankEmailPassBoolean = false;
		StringBuilder blankEmailPassString = new StringBuilder();

		if (StringUtils.isBlank(email.trim())) {
			blankEmailPassString.append("Email");
			blankEmailPassBoolean = true;
		}
		if (StringUtils.isBlank(email.trim())) {
			if (blankEmailPassBoolean) {
				blankEmailPassString.append(", password");
				blankEmailPassBoolean = true;
			} else {
				blankEmailPassString.append("Password");
				blankEmailPassBoolean = true;
			}
		}
		if (blankEmailPassBoolean) {
			blankEmailPassString.append(" cannot be blank.");
			// .toString turn StringBuilder into a string
			throw new InvalidLoginException(blankEmailPassString.toString());
		}

	}

	public void verifySignUp(User user) throws InvalidParameterException {
		logger.info("ValidteUtil.verifySignUp() invoked");

		/*-
		 *  Check if inputs are blank
		 */
		logger.info("check if inputs are blank");

		boolean blankInputBoolean = false;
		StringBuilder blankInputString = new StringBuilder();

		if (StringUtils.isBlank(user.getFirstName().trim())) {
			blankInputString.append("First name");
			blankInputBoolean = true;
		}
		if (StringUtils.isBlank(user.getLastName().trim())) {
			if (blankInputBoolean) {
				blankInputString.append(", last name");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Last name");
				blankInputBoolean = true;
			}
		}
		if (StringUtils.isBlank(user.getEmail().trim())) {
			if (blankInputBoolean) {
				blankInputString.append(", email");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Email");
				blankInputBoolean = true;
			}
		}
		if (StringUtils.isBlank(user.getPassword().trim())) {
			if (blankInputBoolean) {
				blankInputString.append(", password");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Password");
				blankInputBoolean = true;
			}
		}
		if (StringUtils.isBlank(user.getPhoneNumber().trim())) {
			if (blankInputBoolean) {
				blankInputString.append(", phone number");
				blankInputBoolean = true;
			} else {
				blankInputString.append("Phone number");
				blankInputBoolean = true;
			}
		}
		if (blankInputBoolean) {
			blankInputString.append(" cannot be blank.");
			// .toString turn StringBuilder into a string
			throw new InvalidParameterException(blankInputString.toString());
		}

		/*-
		 *  Check if email already exist
		 */
		logger.info("Check if email already exist");

		User databaseUser = userService.getUserByEmail(user.getEmail());

		logger.debug("users {}", user);
		logger.debug("user {}", user);

		logger.debug("in loop");

//		logger.debug("usersE {} user {}", userElement.getEmail(), user.getEmail());

		if(databaseUser != null) {
			if (StringUtils.equalsAnyIgnoreCase(databaseUser.getEmail().trim(), user.getEmail().trim())) {

				throw new InvalidParameterException("Email already exist.");
			}
		}

		/*-
		 *  limit password length
		 */
		logger.info("limit password lengtht");

		if (user.getPassword().length() > 20) {

			throw new InvalidParameterException("Password cannot be more than 20 characters.");
		}

		/*-
		 *  phone number verification
		 */
		logger.info("phone number verification");

		Pattern p = Pattern.compile("^\\d{10}$");
		Matcher m = p.matcher(user.getPhoneNumber().trim());

		if (!m.matches()) {
			throw new InvalidParameterException("Invalid phone number. Phone number must be 10 digits and no symbols.");
		}

		/*-
		 *  email verification
		 */
		logger.info("email verification");

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

		if (!user.getEmail().matches(regex)) {
			throw new InvalidParameterException("Invalid Email.");
		}

		/*-
		 *  user role verification
		 */
		logger.info("user role verification");

		if (user.getUserRole().trim().toLowerCase() != null
				&& !userRoleList.contains(user.getUserRole().trim().toLowerCase())) {
			throw new InvalidParameterException("User role must be 'member' or 'admin'.");
		}

	}

}
