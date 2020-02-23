package com.board.webserivce.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.webserivce.domain.users.UsersRepository;
import com.board.webserivce.dto.users.UsersSaveRequestDto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebController {
	
	private UsersRepository userRepository;
	
	@GetMapping("/")
	public String init() {
		return "Hello World";
	}
	
	@PostMapping("/users")
	public void saveUsers(@RequestBody UsersSaveRequestDto dto) {
		userRepository.save(dto.toEntity());
	}
}
