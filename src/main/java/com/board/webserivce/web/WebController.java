package com.board.webserivce.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.board.webserivce.dto.users.UsersSaveRequestDto;
import com.board.webserivce.service.UserSecurityService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {
	private UserSecurityService userSecurityService;
	
	@GetMapping("/")
	public String init() {
		return "contents/index";
	}
	
	@GetMapping("/hello")
	public String hello() {
		System.out.println("??");
		return "contents/hello";
	}
	
	@GetMapping("/login/error")
	public String error() {
		System.out.println("??");
		return "contents/error";
	}
	

}
