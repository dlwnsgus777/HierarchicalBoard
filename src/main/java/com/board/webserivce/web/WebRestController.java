package com.board.webserivce.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.board.webserivce.domain.users.Users;
import com.board.webserivce.domain.users.UsersRepository;
import com.board.webserivce.dto.users.UsersSaveRequestDto;
import com.board.webserivce.service.UserSecurityService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	private UsersRepository re;
	private UserSecurityService userSecurityService;
	
	@PostMapping("/users/signup")
	public ResponseEntity<Map<String, Object>> saveUsers(@RequestBody UsersSaveRequestDto dto) {
		userSecurityService.accountUser(dto);
		Map<String, Object> map = new HashMap<>();
		
		map.put("msg", "save");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
}
