package com.board.webserivce.dto.boards;

import java.time.LocalDate;
import java.util.List;

import com.board.webserivce.domain.users.Users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardsFindAllResponseDto {
	private Long id;
	private String title;
	private String content;
	private Long authorId;
	private int depth;
	private String authorName;
	private LocalDate createdDate;
}
