package com.board.webserivce.domain.boards;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ManyToAny;

import com.board.webserivce.domain.BaseTimeEntity;
import com.board.webserivce.domain.images.Images;
import com.board.webserivce.domain.users.Users;
import com.board.webserivce.domain.users.Users.UsersBuilder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DynamicUpdate
public class Boards extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ColumnDefault("0")
	private int depth;
	
	@Column(length = 200, nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	@ColumnDefault("null")
	private Long parentId;
	
	@ColumnDefault("N")
	private String delYn;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Users author;
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "boardId")
	private List<Images> images = new ArrayList<>();
	
	@Builder
	public Boards(String content, String title, Long parentId, int depth, Users author, String delYn) {
		this.title = title;
		this.content = content;
		this.parentId = parentId;
		this.depth = depth;
		this.author = author;
		this.delYn = delYn;
	}
	
	public void deleteUserPost() {
		this.title = "삭제 되었습니다.";
		this.author = null;
		this.images.clear();
	}
	
	public void updatePost(String title, String content) {
		if (title != null) {
			this.title = title;
		}
		
		this.content = content == null ? "" : content;
		
	}
	
	public void deletePost() {
		this.title = "삭제 되었습니다.";
		this.delYn = "Y";
	}
	
	public void deleteImage() {
		this.images.clear();
	}
}
