package com.board.webserivce.dto.boards;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.board.webserivce.domain.boards.Boards;
import com.board.webserivce.domain.users.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardsFindAllResponseDto {
	private Long id;
	private String title;
	private Long authorId;
	private int depth;
	private String authorName;
	private LocalDate createdDate;
	private String delYn;
	
	public void converEntityToDto(Boards boards) {
		this.id = boards.getId();
		this.title = boards.getTitle();
		this.authorId = boards.getAuthor() == null ? null :  boards.getAuthor().getId();
		this.depth = boards.getDepth();
		this.authorName = boards.getAuthor() == null ? null :boards.getAuthor().getUserName();
		this.createdDate = boards.getCreatedDate();
		this.delYn = boards.getDelYn();
	}
}
