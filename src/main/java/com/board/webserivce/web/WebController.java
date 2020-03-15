package com.board.webserivce.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.board.webserivce.domain.boards.Boards;
import com.board.webserivce.domain.boards.BoardsRepository;
import com.board.webserivce.domain.users.Users;
import com.board.webserivce.domain.users.UsersRepository;
import com.board.webserivce.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class WebController {
	private UsersRepository userRepository;
	private BoardsRepository bb;
	private BoardService bs;
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
		return "contents/error";
	}
	
	@PostMapping("/login/fail")
	public String initPost() {
		return "contents/index";
	}
	
	@GetMapping("/info")
	public String info(Principal principal, ModelMap model) {
		Optional<Users> users = userRepository.findByUserId(principal.getName());
		Users user = users.get();
		
		model.addAttribute("userName", user.getUserName());

		return "contents/info";
	}
	
	@GetMapping("/board/{id}")
	public String getBoardDetail(@PathVariable int id, ModelMap model) {
		
		model.addAttribute("path", id);
		return "contents/boardDetail";
	}
	
	@GetMapping("/test")
	public String test(Principal prin) {
		Optional<Users> users = userRepository.findByUserId(prin.getName());
		Users user = users.get();
		Boards bo = Boards.builder().title("test").content("test content").depth(0).p_id(null).author_id(user.getId()).build();
		bb.save(bo);
		user.addBoard(bb.findAll().get(0));
		
		return "contents/info";
	}
	
	@GetMapping("/testdel")
	public String testdel(Principal prin) {
		Optional<Users> users = userRepository.findByUserId(prin.getName());
		Users user = users.get();
		System.out.println("-----------------------------test");
		List<Boards> bo = user.getBoards();
		for(Boards bbb : bo) {
			System.out.println(bbb.getTitle());
		}
		bo.remove(bo.get(0));
		System.out.println("-----------------------------test");
		List<Boards> boc = user.getBoards();
		for(Boards bbb : boc) {
			System.out.println(bbb.getTitle());
		}
		return "contents/info";
	}
	
}
