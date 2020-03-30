package com.board.webserivce.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;

public class PageUtils {

	public static Map<String, Object> getPages(Pageable page) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		int size = page.getPageSize();
		int pageNumber = page.getPageNumber();
		int inPage = (pageNumber - 1) / size + 1;
		int startPage = (inPage - 1) + 1;
		int endPage = size * inPage;
		pageMap.put("startPage", startPage);
		pageMap.put("endPage", endPage);
		return pageMap;
	}
}
