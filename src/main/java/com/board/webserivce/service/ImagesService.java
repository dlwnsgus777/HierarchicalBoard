package com.board.webserivce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.board.webserivce.domain.boards.BoardsRepository;
import com.board.webserivce.domain.users.UsersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImagesService {
	
	@Transactional
	public void saveImages(List<MultipartFile> images, Long boardId) {
		
	}
}
