package com.board.webserivce.dto.boards;

import java.time.LocalDate;

import com.board.webserivce.domain.users.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardsFindResponseDto {
	private String title;
	private String content;
	private Long authorId;
	private int depth;
	private Long parentId;
	private String authorName;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
}