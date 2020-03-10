package com.board.webserivce.domain.users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.webserivce.dto.users.UsersSaveRequestDto;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUserId(String userId);
	Long deleteByUserId(String userId);
}
