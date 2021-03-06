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
import com.board.webserivce.dto.images.ImagesSaveRequestDto;

import ch.qos.logback.core.pattern.Converter;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
	private BoardsRepository boardRepository;
	private UsersRepository usersRepository;
	
	@Transactional
	public Long savePost(BoardsSaveRequestDto boardDto, String userId) {
		Users user = usersRepository.findByUserId(userId).get();
		
		boardDto.setAuthor(user);
		
		if (boardDto.getParentId() != null) {
			Optional<Boards> parentBoard = boardRepository.findById(boardDto.getParentId());
			boardDto.setHierarchicalDepth(parentBoard.get().getDepth());	
		}
		
		Boards board = boardRepository.save(boardDto.toEntity());

		return board.getId();
	}
	
	@Transactional
	public void deletePostAfterDelUser(Long userId) {
		List<Boards> boards = boardRepository.findByAuthorId(userId);
		for(Boards board: boards) {
			board.deleteUserPost();
		}
	}
	
	@Transactional
	public BoardsFindResponseDto findPost(int boardId) {
		ModelMapper modelmapper = new ModelMapper();
		Boards boards = boardRepository.findById((long)boardId);
		BoardsFindResponseDto boardsDto = modelmapper.map(boards, BoardsFindResponseDto.class);
			
		return boardsDto;
	}
	
	@Transactional
	public Page<BoardsFindAllResponseDto> findAllPost(int page) {
		int pageNumber = page - 1;
		Pageable pageAble = PageRequest.of(pageNumber, 10);
		Page<Boards> boards = boardRepository.findAllBoards(pageAble);
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
	
	@Transactional
	public void deletePost(Long postId, String userId) {
		Users user = usersRepository.findByUserId(userId).get();
		Boards board = boardRepository.findById(postId).get();
		
		if (user.getUserId().equals(board.getAuthor().getUserId()) ) {
			board.deletePost();
		}
	}
	
	@Transactional
	public void modifiedPost(BoardsSaveRequestDto boardDto, 
							ImagesSaveRequestDto imageDto,
							String userId,
							Long postId) {
		
		Users user = usersRepository.findByUserId(userId).get();
		Boards board = boardRepository.findById(postId).get();
		
		if (user.getUserId().equals(board.getAuthor().getUserId()) ) {
			// 수정 로직
			board.updatePost(boardDto.getTitle(), boardDto.getContent());
			board.deleteImage();
		}
	}
}
