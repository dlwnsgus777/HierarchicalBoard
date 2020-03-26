package com.board.webserivce.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class ImagesSaveDto {
	private Long boardId;
	private List<MultipartFile> images;
}
