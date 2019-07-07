package com.amby.rest.security.jwt;

import com.amby.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	@Autowired
	UserService userService;

	static {
		inMemoryUserList.add(new JwtUserDetails(1L, "ambrish",
				"$2a$10$oOXB82g1r/kfXKTegPL6LO43vQmeY64zcA41Z6wjc4jF1CAttAuTG", "ROLE_USER_2")); // password = 123
		inMemoryUserList.add(new JwtUserDetails(2L, "msdhoni",
				"$2a$10$oOXB82g1r/kfXKTegPL6LO43vQmeY64zcA41Z6wjc4jF1CAttAuTG", "ROLE_USER_2")); // password = 123
		inMemoryUserList.add(new JwtUserDetails(3L, "narendra",
				"$2a$10$oOXB82g1r/kfXKTegPL6LO43vQmeY64zcA41Z6wjc4jF1CAttAuTG", "ROLE_USER_2")); // password = 123
		
		//$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

}
