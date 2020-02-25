package com.board.webserivce.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {
	
	@GetMapping("/")
	public String init() {
		return "contents/index";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "contents/test";
	}

}
