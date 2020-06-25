package com.dev.YimJ.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.dev.YimJ.AuthException;
import com.dev.YimJ.AuthenticationService;
import com.dev.YimJ.PasswordChangeService;
import com.dev.YimJ.UserNotFoundException;

public class MainByXml {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:config.xml");
		AuthenticationService authSvc = ctx.getBean("authenticationService", AuthenticationService.class);
		
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "1111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "11111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "111111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "1111111");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "11111111");
		try {
			authSvc.authenticate("bkchoi2", "1111");
		} catch (UserNotFoundException e) {
		}
		authSvc.authenticate("bkchoi", "asdf");
		PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
		
		pwChgSvc.changePassword("bkchoi", "asdf", "asdfasdf");
		runAuthAndCatchAuthEx(authSvc, "bkchoi", "asdf");
		authSvc.authenticate("bkchoi", "asdfasdf");
		ctx.close();
	}
	
		private static void runAuthAndCatchAuthEx(AuthenticationService authSvc, String userId, String password) {
		try {
			authSvc.authenticate(userId, password);
		} catch (AuthException e) {
		}
	}
}
