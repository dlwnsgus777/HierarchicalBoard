package com.board.webserivce.domain;


import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.board.webserivce.domain.users.Users;
import com.board.webserivce.domain.users.UsersRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UsersRepositoryTest {
	
	@Autowired
	UsersRepository userRepository;
	
	@AfterEach
	public void cleanup() {
		userRepository.deleteAll();
	}
	
	@Test
	public void addUsers() {
		//given
		userRepository.save(Users.builder()
							.userId("test")
							.password("test")
							.userName("test¿ë")
							.build());
		
		//when
		List<Users> usersList = userRepository.findAll();
		
		//then
		Users users = usersList.get(0);
		assertThat(users.getUserId(), is("test"));
		assertThat(users.getUserName(), is("test¿ë")); 
	}
}