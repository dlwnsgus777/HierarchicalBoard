package com.board.webserivce.service;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.webserivce.domain.boards.Boards;
import com.board.webserivce.domain.boards.BoardsRepository;
import com.board.webserivce.domain.users.Users;
import com.board.webserivce.domain.users.UsersRepository;
import com.board.webserivce.dto.boards.BoardsFindAllResponseDto;
import com.board.webserivce.dto.boards.BoardsFindResponseDto;
import com.board.webserivce.dto.boards.BoardsSaveRequestDto;

import ch.qos.logback.core.pattern.Converter;
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
	public BoardsFindResponseDto findPost(int boardId) {
		ModelMapper modelmapper = new ModelMapper();
		Boards boards = boardRepository.findById((long)boardId);
		BoardsFindResponseDto boardsDto = modelmapper.map(boards, BoardsFindResponseDto.class);
			
		return boardsDto;
	}
	
	@Transactional// Page<Boards>
	public Page<BoardsFindAllResponseDto> findAllPost(int page) {
		int pageNumber = page - 1;
		Pageable pageAble = PageRequest.of(pageNumber, 10);
		System.out.println(page);
		//ModelMapper modelmapper = new ModelMapper();
		//Type listType = new TypeToken<Page<BoardsFindAllResponseDto>>(){}.getType();
		Page<Boards> boards = boardRepository.findAllBoards(pageAble);
		//Page<BoardsFindAllResponseDto> boardsDto = modelmapper.map(boards, listType);
		Page<BoardsFindAllResponseDto> boardsDto = boards.map(new Function<Boards, BoardsFindAllResponseDto>() {

			@Override
			public BoardsFindAllResponseDto apply(Boards t) {
				// TODO Auto-generated method stub
				BoardsFindAllResponseDto dto = new BoardsFindAllResponseDto();
				dto.converEntityToDto(t);
				return dto;
			}
			
		});

		return boardsDto;
	}
}
