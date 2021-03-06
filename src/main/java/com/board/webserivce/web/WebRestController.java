package com.board.webserivce.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.board.webserivce.dto.boards.BoardsSaveRequestDto;
import com.board.webserivce.dto.images.ImagesSaveRequestDto;
import com.board.webserivce.dto.users.UsersSaveRequestDto;
import com.board.webserivce.service.BoardService;
import com.board.webserivce.service.ImagesService;
import com.board.webserivce.service.UserSecurityService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	private UserSecurityService userSecurityService;
	private BoardService boardService;
	private ImagesService imagesService;
	
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
	
	@PostMapping("/post/save")
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
}
