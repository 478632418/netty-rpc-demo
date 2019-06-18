package com.dx.service.impl;

import com.dx.service.HelloService;

public class HelloServiceImpl implements HelloService {
	public String hello(String msg) {
		return msg != null ? msg + " -----> I am fine." : "I am fine.";
	}
}
