package com.board.webserivce.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.webserivce.domain.boards.Boards;
import com.board.webserivce.domain.boards.BoardsRepository;

@Service
public class BoardService {
	private BoardsRepository bo;
	@Transactional
	public void del(Boards bb) {
		System.out.println("tq" + bb.getId());
		bo.deleteById(bb.getId());
		
	}
}
