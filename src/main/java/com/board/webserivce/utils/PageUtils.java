package com.board.webserivce.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public class PageUtils {
	static int pageScale = 5;
	
	public static Map<String, Object> getPages(Pageable page, int totalPage) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		int size = page.getPageSize();
		int pageNumber = page.getPageNumber();
		int off = (int) page.getOffset();
		int startPage = ((pageNumber - 1)   / pageScale) * pageScale + 1;
		int endPage = startPage + pageScale - 1;
		
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		int inPage = (pageNumber - 1) / size + 1;
//		int startPage = (inPage - 1) * pageScale; 
//		int endPage = inPage * pageScale;
//		System.out.println("startPageTest : " + startPageTest +  ", " + endPageTest);
		System.out.println(pageNumber +" : " + startPage + ", " + endPage);
		pageMap.put("startPage", startPage);
		pageMap.put("endPage", endPage);
		return pageMap;
	}
}
