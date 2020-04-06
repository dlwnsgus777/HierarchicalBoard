package com.board.webserivce.dto.boards;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.board.webserivce.domain.boards.Boards;
import com.board.webserivce.domain.users.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class BoardsSaveRequestDto {
	private String title;
	private String content;
	private Users author;
	private int depth;
	private Long parentId;
	private String delYn = "N";
	
	
	public Boards toEntity() {
		return Boards.builder()
				.title(title)
				.content(content)
				.author(author)
				.depth(depth)
				.parentId(parentId)
				.delYn(delYn)
				.build();
	}
	
	@Builder
	public BoardsSaveRequestDto(String content, String title, Long parentId, int depth, Users author) {
		this.title = title;
		this.content = content;
		this.parentId = parentId;
		this.depth = depth;
		this.author = author;
	}
	
	public void setHierarchicalDepth(int depth) {
		this.depth = depth + 1;
	}
	
}
