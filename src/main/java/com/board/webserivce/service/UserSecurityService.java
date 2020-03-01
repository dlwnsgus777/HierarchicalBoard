package com.board.webserivce.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.webserivce.domain.users.Users;
import com.board.webserivce.domain.users.UsersRepository;
import com.board.webserivce.dto.users.UsersSaveRequestDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService  {
	private UsersRepository usersRepository;
	
	@Transactional
	public Long accountUser(UsersSaveRequestDto saveDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		saveDto.setPassword(passwordEncoder.encode(saveDto.getPassword()));
		
		return usersRepository.save(saveDto.toEntity()).getId();
	}
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Users> userEntitiy = usersRepository.findByUserId(userId);
		Users user = userEntitiy.get();
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new User(user.getUserId(), user.getPassword(), authorities);
	}

}
