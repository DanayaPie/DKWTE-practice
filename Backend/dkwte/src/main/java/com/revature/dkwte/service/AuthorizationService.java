package com.revature.dkwte.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dkwte.exception.UnauthorizedException;
import com.revature.dkwte.model.User;

public class AuthorizationService {

//	private Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
//
//	/*-
//	 *  Type of exception when...
//	 *  1. user is not finance manager nor employee can access
//	 *  2. user is not finance manager
//	 *  3. user is not employee
//	 */
//
//	// 1.
//	public void authorizeFinanceManagerAndEmployee(User user) throws UnauthorizedException {
//		logger.info("AuthorizationService.authorizeFinanceManagerAndEmployee() invoked");
//		
//		if (user == null || !(user.getRoll.getRoll().equals(UserRoleConstants.EMPLOYEE) || user.getUserRole().equals(UserRoleConstants.FINANCE_MANAGER))) {
//			throw new UnauthorizedException("Must log in to access.");
//		}
//	}
//
//	// 2. 
//	public void authorizeFinanceManager(User user) throws UnauthorizedException {
//		logger.info("AuthorizationService.authorizeFinanceManager() invoked");
//		
//		if (user == null || !(user.getUserRole().equals(UserRoleConstants.FINANCE_MANAGER))) {
//			throw new UnauthorizedException("Must logged in as finance manager to access.");
//		}
//	}
//
//	// 3.
//	public void authorizeEmployee(User user) throws UnauthorizedException {
//		logger.info("AuthorizationService.authorizeEmployee() invoked");
//		
//		if (user == null || !(user.getUserRole().equals(UserRoleConstants.EMPLOYEE))) {
//			throw new UnauthorizedException("Must logged in as employee to access");
//		}
//	}
}
