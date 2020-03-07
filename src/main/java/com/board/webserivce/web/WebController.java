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
	
	@GetMapping("/board")
	public String hello() {
		return "contents/board";
	}
	
	@GetMapping("/login/error")
	public String error() {
		System.out.println("??");
		return "contents/error";
	}
	
	@PostMapping("/login/fail")
	public String initPost() {
		return "contents/index";
	}
	
	@GetMapping("/info")
	public String info() {
		return "contents/info";
	}

}
