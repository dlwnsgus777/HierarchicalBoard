package com.board.webserivce.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void 메인페이지() {
		//when
		String body = this.testRestTemplate.getForObject("/", String.class);
		
		//then
		assertThat(body).contains("테스트해보자");
		
	}
}
