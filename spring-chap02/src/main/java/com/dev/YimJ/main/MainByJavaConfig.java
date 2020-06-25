package com.dev.YimJ.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dev.YimJ.AuthenticationService;
import com.dev.YimJ.PasswordChangeService;

public class MainByJavaConfig {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		
		AuthenticationService authSvc = ctx.getBean("authenticationService", AuthenticationService.class);
		authSvc.authenticate("bkchoi", "asdf");
		
		PasswordChangeService pwChgSvc = ctx.getBean(PasswordChangeService.class);
		pwChgSvc.changePassword("bkchoi", "asdf", "asdfasdf");
		
		ctx.close();
	}
}
