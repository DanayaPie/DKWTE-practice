package com.revature.dkwte.utility;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dkwte.exception.InvalidLoginException;

public class ValidateUtil {

	private static Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

	public static void verifyEmailAndPassword(String email, String password) throws InvalidLoginException {
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

}
