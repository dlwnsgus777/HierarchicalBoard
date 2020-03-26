package com.board.webserivce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ImagePathConfig implements WebMvcConfigurer {
	
	@Value("${custom.path.location-path}")
	private String uploadImagePath;
	
	@Value("${custom.path.uri-path}")
	private String uriImagePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(uriImagePath + "/**")
				.addResourceLocations("file:///C:/images/").setCachePeriod(0);// + uploadImagePath
	}
}
