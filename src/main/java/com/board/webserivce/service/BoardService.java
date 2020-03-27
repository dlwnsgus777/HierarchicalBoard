package com.board.webserivce.service;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.webserivce.domain.boards.Boards;
import com.board.webserivce.domain.boards.BoardsRepository;
import com.board.webserivce.domain.users.Users;
import com.board.webserivce.domain.users.UsersRepository;
import com.board.webserivce.dto.boards.BoardsFindAllResponseDto;
import com.board.webserivce.dto.boards.BoardsFindResponseDto;
import com.board.webserivce.dto.boards.BoardsSaveRequestDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
	private BoardsRepository boardRepository;
	private UsersRepository usersRepository;
	
	@Transactional
	public Long savePost(BoardsSaveRequestDto boardDto, Principal principal) {
		String userId = principal.getName();
		Users user = usersRepository.findByUserId(userId).get();
		
		boardDto.setAuthor(user);
		Boards board = boardRepository.save(boardDto.toEntity());
		
		return board.getId();
	}
	
	@Transactional
	public void deletePostAfterDelUser(Long userId) {
		List<Boards> boards = boardRepository.findByAuthorId(userId);
		for(Boards board: boards) {
			board.deleteBoard();
		}
	}
	
	@Transactional
	public List<BoardsFindAllResponseDto> findAllPost() {
		ModelMapper modelmapper = new ModelMapper();
		Type listType = new TypeToken<List<BoardsFindAllResponseDto>>(){}.getType();
		List<Boards> boards = boardRepository.findAllBoard();
		List<BoardsFindAllResponseDto> boardsDto = modelmapper.map(boards, listType);
			
		return boardsDto;
	}
}
