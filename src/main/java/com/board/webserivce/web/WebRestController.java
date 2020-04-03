package com.board.webserivce.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.webserivce.domain.boards.Boards;
import com.board.webserivce.domain.boards.BoardsRepository;
import com.board.webserivce.dto.boards.BoardsFindAllResponseDto;
import com.board.webserivce.dto.boards.BoardsFindResponseDto;
import com.board.webserivce.dto.boards.BoardsSaveRequestDto;
import com.board.webserivce.dto.images.ImagesSaveRequestDto;
import com.board.webserivce.dto.users.UsersSaveRequestDto;
import com.board.webserivce.service.BoardService;
import com.board.webserivce.service.ImagesService;
import com.board.webserivce.service.UserSecurityService;
import com.board.webserivce.utils.PageUtils;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	private UserSecurityService userSecurityService;
	private BoardService boardService;
	private ImagesService imagesService;
	private BoardsRepository bb;
	
	
	@PostMapping("/users/signup")
	public ResponseEntity<Map<String, Object>> saveUsers(@RequestBody UsersSaveRequestDto dto) {
		userSecurityService.accountUser(dto);
		Map<String, Object> map = new HashMap<>();
		
		map.put("msg", "save");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@GetMapping("/user/userName")
	public ResponseEntity<Map<String, Object>> getUserName(Principal principal) {
		Map<String, Object> map = new HashMap<>();
		map.put("userName", principal.getName());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@PostMapping("/user/changeName")
	public ResponseEntity<Map<String, Object>> chageUserName(@RequestBody Map<String, Object> map,
			Principal principal) {
		String changeName = map.get("changeName").toString();
		String userId = principal.getName();
		
		userSecurityService.changeUserName(userId, changeName);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("msg", "success");
		
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<Map<String, Object>> deleteUser(Principal principal) {
		String userId = principal.getName();
		userSecurityService.deleteUser(userId);
		Map<String, Object> responseMap = new HashMap<>();
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@PostMapping("/post/save")//@RequestParam("images") List<MultipartFile> images,
	public ResponseEntity<Map<String, Object>> test(BoardsSaveRequestDto boardDto, ImagesSaveRequestDto imageDto, Principal principal) {
		String userId = principal.getName();
		Long boardId = boardService.savePost(boardDto, userId);
		long checkFileSize = imageDto.getImages().get(0).getSize();
		
		if (checkFileSize > 0) {
			imagesService.saveImages(imageDto, boardId);
		}
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("msg", "save");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{id}")
	public ResponseEntity<Map<String, Object>> deletePost(@PathVariable Long id, Principal principal) {
		String userId = principal.getName();
		boardService.deletePost(id, userId);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("msg", "success");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	} 
	
	@PostMapping("/post/{id}")
	public ResponseEntity<Map<String, Object>> modifiedPost(@PathVariable Long id, 
									BoardsSaveRequestDto boardDto, 
									ImagesSaveRequestDto imageDto,
									Principal principal) {
		String userId = principal.getName();
		boardService.modifiedPost(boardDto, imageDto, userId, id);
		
		long checkFileSize = imageDto.getImages().get(0).getSize();
		
		if (checkFileSize > 0) {
			imagesService.saveImages(imageDto, id);
		}
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("msg", "success");
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	} 
	
//	@GetMapping("/test")
//	public ResponseEntity<Map<String, Object>> test(Principal prin, ModelMap model) {
//		BoardsFindResponseDto boards = boardService.findPost(13);
//		model.addAttribute("posts", boards);
//		model.addAttribute("msg", "success");
//		return  new ResponseEntity<>(model, HttpStatus.OK);
//	}
	
	@GetMapping("/test")
	public ResponseEntity<Map<String, Object>> test(Principal prin, ModelMap model) {
		Page<BoardsFindAllResponseDto>  boards = boardService.findAllPost(1);
		Pageable page = boards.getPageable();
		model.addAttribute("page", PageUtils.getPages(page, boards.getTotalPages()));
		model.addAttribute("posts", boards);
		model.addAttribute("msg", "success");
		return  new ResponseEntity<>(model, HttpStatus.OK);
	}
	
	@GetMapping("/user/post/test")
	public ResponseEntity<Map<String, Object>> getPost(Principal prin, ModelMap model) {
		List<Boards> boards = bb.findAllBoard();
//		Users user = users.get();
//		Boards bo = Boards.builder().title("tq").content("test content").depth(0).parentId(null).authorId(user.getId()).build();
//		bb.save(bo);
//		user.addBoard(bb.findAll().get(0));
		model.addAttribute("test", boards);
		return  new ResponseEntity<>(model, HttpStatus.OK);
	}
}
