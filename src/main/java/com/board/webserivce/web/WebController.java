package com.board.webserivce.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
	
	@GetMapping("/")
	public String init() {
		return "Hello World";
	}
}
