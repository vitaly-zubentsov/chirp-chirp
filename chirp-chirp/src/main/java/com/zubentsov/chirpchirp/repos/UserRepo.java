package com.zubentsov.chirpchirp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zubentsov.chirpchirp.domen.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
