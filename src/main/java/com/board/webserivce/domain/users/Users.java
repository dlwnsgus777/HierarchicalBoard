package com.board.webserivce.domain.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;

import com.board.webserivce.domain.BaseTimeEntity;
import com.board.webserivce.domain.boards.Boards;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@DynamicUpdate
public class Users extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20,unique = true, nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String password;
	
	@Column(unique = true)
	private String userName;
	
	@OneToMany(mappedBy = "author_id")
	private List<Boards> boards = new ArrayList<>();
	
	@Builder
	public Users(String userId, String password, String userName) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
	}
	
	public void changeUserName(String userName) {
		this.userName = userName;
	}
}
