package com.board.webserivce.dto.boards;

import com.board.webserivce.domain.boards.Boards;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BoardsSaveRequestDto {
	private String title;
	private String content;
	
	public Boards toEntity() {
		return Boards.builder()
				.title(title)
				.content(content)
				.build();
	}
}
