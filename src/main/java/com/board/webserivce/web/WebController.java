package com.board.webserivce.web;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
//	@GetMapping("/test")
//	public String test(Principal prin, ModelMap model) {
//		List<Boards> boards = bb.findAllBoard();
//		Users user = users.get();
//		Boards bo = Boards.builder().title("tq").content("test content").depth(0).parentId(null).authorId(user.getId()).build();
//		bb.save(bo);
//		user.addBoard(bb.findAll().get(0));
//		model.addAttribute("test", boards);
//		return  "contents/info";
//	}
	
}
