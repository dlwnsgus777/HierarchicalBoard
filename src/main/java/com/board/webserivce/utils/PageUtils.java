package com.board.webserivce.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public class PageUtils {
	static int pageScale = 5;
	
	public static Map<String, Object> getPages(Pageable page, int totalPage) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		int pageNumber = page.getPageNumber() + 1;
		int startPage = ((pageNumber - 1)   / pageScale) * pageScale;
		int endPage = startPage + pageScale- 1;
		
		if (endPage >= totalPage) {
			endPage = totalPage;
		}
		
		System.out.println(pageNumber +" : " + startPage + ", " + endPage);
		pageMap.put("startPage", startPage);
		pageMap.put("endPage", endPage);
		return pageMap;
	}
}
