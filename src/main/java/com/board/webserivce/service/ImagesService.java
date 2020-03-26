package com.board.webserivce.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.board.webserivce.domain.boards.BoardsRepository;
import com.board.webserivce.domain.images.ImagesRepository;
import com.board.webserivce.domain.users.UsersRepository;
import com.board.webserivce.dto.images.ImagesSaveRequestDto;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagesService {
	@Resource
	private ImagesRepository imagesRepository;
	
	private String uploadPath = "C://images/";
	
	@Transactional
	public void saveImages(ImagesSaveRequestDto imageDto, Long boardId) {
		List<MultipartFile> images = imageDto.getImages();
		for (MultipartFile image: images) {
			try {
				String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
				File file = new File(uploadPath + fileName);
				File dir = new File(uploadPath);
				
				if (!dir.isDirectory()) {
					dir.mkdir();
				}
				image.transferTo(file);
				imageDto.setImageDto(fileName, boardId);
				System.out.println(imageDto.getFileName());
				imagesRepository.save(imageDto.toEntity());
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
