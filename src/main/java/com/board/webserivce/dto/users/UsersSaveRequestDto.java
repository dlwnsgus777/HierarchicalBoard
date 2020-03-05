package com.board.webserivce.dto.users;

import com.board.webserivce.domain.users.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersSaveRequestDto {
	
	private String userId;
	private String password;
	private String userName;
	
	public Users toEntity() {
		return Users.builder()
				.userId(userId)
				.password(password)
				.userName(userName)
				.build();
	}
	
	@Builder
	public UsersSaveRequestDto(String userId, String password, String userName) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
	}
}
