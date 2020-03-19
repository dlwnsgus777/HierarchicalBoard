package com.board.webserivce.service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.webserivce.domain.boards.Boards;
import com.board.webserivce.domain.boards.BoardsRepository;
import com.board.webserivce.domain.users.Users;
import com.board.webserivce.domain.users.UsersRepository;
import com.board.webserivce.dto.boards.BoardsSaveRequestDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
	private BoardsRepository boardRepository;
	private UsersRepository usersRepository;
	
	@Transactional
	public void savePost(BoardsSaveRequestDto boardDto, Principal principal) {
		String userId = principal.getName();
		Users user = usersRepository.findByUserId(userId).get();

		boardDto.setAuthorId(user.getId());
		boardRepository.save(boardDto.toEntity());
	}
}
