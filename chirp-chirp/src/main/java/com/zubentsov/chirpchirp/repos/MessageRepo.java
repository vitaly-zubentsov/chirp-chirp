package com.zubentsov.chirpchirp.repos;

import org.springframework.data.repository.CrudRepository;

import com.zubentsov.chirpchirp.domen.Message;

public interface MessageRepo extends CrudRepository<Message, Long> {

}
