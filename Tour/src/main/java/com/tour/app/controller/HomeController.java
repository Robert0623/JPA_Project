package com.tour.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 이것은 홈이다.
@Controller
public class HomeController 
{
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("login")
	public String login() {
		return "/user/login";
	}

}
