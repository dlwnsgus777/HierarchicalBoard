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

import lombok.AllArgsConstructor;

@Service
//@AllArgsConstructor
public class ImagesService {
	private ImagesRepository imagesRepository;
	
	private String uploadPath = "C://images/";
	
	@Transactional
	public void saveImages(List<MultipartFile> images, Long boardId) {
		MultipartFile image = images.get(0);
		String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
		//File file = new File(uploadImagePath + fileName);
		//System.out.println(uploadImagePath);
		File file = new File(uploadPath + fileName);
		File dir = new File(uploadPath);
		if (!dir.isDirectory()) {
			dir.mkdir();
		}
		System.out.println(file.isDirectory());
		System.out.println(dir.isDirectory());
		try {
			image.transferTo(file);
			//InputStream fileStream = image.getInputStream();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
