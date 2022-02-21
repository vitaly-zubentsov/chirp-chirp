package com.zubentsov.chirpchirp.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.zubentsov.chirpchirp.domen.Message;

public interface MessageRepo extends CrudRepository<Message, Long> {
	
	List<Message> findByTag(String tag);

}
