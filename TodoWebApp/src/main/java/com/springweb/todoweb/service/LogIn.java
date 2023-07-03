package com.springweb.todoweb.service;

import org.springframework.stereotype.Service;

@Service
public class LogIn {

	public boolean checkAuthentication(String user, String password) {
		boolean chkname = user.equalsIgnoreCase("Saurabh");
		boolean chkpassword = password.equalsIgnoreCase("pathak");

		return chkname && chkpassword;

	}
}
