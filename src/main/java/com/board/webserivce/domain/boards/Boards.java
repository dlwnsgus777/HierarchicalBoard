package com.board.webserivce.domain.boards;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ManyToAny;

import com.board.webserivce.domain.BaseTimeEntity;
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
	private Long p_id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users author_id;
	
	@Builder
	public Boards(String content, String title, int author_id) {
		this.userId = userId;
		this.password = password;
		this.author_id = author_id;
	}
}
