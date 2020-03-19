package com.board.webserivce.dto.boards;

import com.board.webserivce.domain.boards.Boards;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BoardsSaveRequestDto {
	private String title;
	private String content;
	private Long authorId;
	private int depth;
	private Long parentId;
	
	public Boards toEntity() {
		return Boards.builder()
				.title(title)
				.content(content)
				.authorId(authorId)
				.depth(depth)
				.parentId(parentId)
				.build();
	}
	
	@Builder
	public BoardsSaveRequestDto(String content, String title, Long parentId, int depth, Long authorId) {
		this.title = title;
		this.content = content;
		this.parentId = parentId;
		this.depth = depth;
		this.authorId = authorId;
	}
}
