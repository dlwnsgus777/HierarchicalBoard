package com.board.webserivce.dto.images;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.board.webserivce.domain.images.Images;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class ImagesSaveRequestDto {
	private String fileName;
	private String fileType;
	private Long boardId; 
	private List<MultipartFile> images;
	
	public Images toEntity() {
		return Images.builder()
				.fileName(fileName)
				.boardId(boardId)
				.build();
	}
	
	public void setImageDto(String fileName, Long boardId) {
		this.fileName = fileName;
		this.boardId = boardId;
	}
}
