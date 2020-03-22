package com.board.webserivce.domain.boards;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardsRepository extends JpaRepository<Boards, Long> {
	Long deleteById(int id);
	
	@Query(value = "WITH RECURSIVE CTS AS (\r\n" + 
			"	SELECT  id\r\n" + 
			"		   ,title\r\n" + 
			"           ,content\r\n" + 
			"           ,depth\r\n" + 
			"           ,parent_id\r\n" + 
			"           ,author_id\r\n" + 
			"		   ,created_date\r\n" + 
			"           ,modified_date\r\n" + 
			"           ,CAST(id as CHAR(255)) lvl\r\n" + 
			"	FROM boards\r\n" + 
			"    WHERE parent_id IS NULL\r\n" + 
			"    UNION ALL\r\n" + 
			"    SELECT  b.id\r\n" + 
			"		   ,b.title\r\n" + 
			"           ,b.content\r\n" + 
			"           ,b.depth\r\n" + 
			"           ,b.parent_id\r\n" + 
			"           ,b.author_id\r\n" + 
			"           ,b.created_date\r\n" + 
			"           ,b.modified_date\r\n" + 
			"           ,CONCAT(c.lvl, \",\", b.id) lvl\r\n" + 
			"	FROM boards b\r\n" + 
			"	INNER JOIN CTS c\r\n" + 
			"	ON b.parent_id = c.id\r\n" + 
			")\r\n" + 
			"SELECT b.id\r\n" + 
			"	  ,title\r\n" + 
			"      ,content\r\n" + 
			"      ,depth\r\n" + 
			"      ,parent_id\r\n" + 
			"      ,b.created_date\r\n" + 
			"      ,b.modified_date\r\n" + 
			"	  ,author_id\r\n" + 
//			"      ,u.user_name\r\n" + 
			"from cts as b\r\n" + 
//			"join users as u\r\n" + 
//			"on b.author_id = u.id\r\n" + 
			"ORDER BY lvl",nativeQuery = true)
	List<Boards> findAllBoard();
	List<Boards> findByAuthorId(Long userId);
}
