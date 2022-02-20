package com.zubentsov.chirpchirp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zubentsov.chirpchirp.domen.Message;
import com.zubentsov.chirpchirp.repos.MessageRepo;

@Controller
public class GreetingController {

	@Autowired
	MessageRepo messageRepo;

	@GetMapping("/greeting")
	public String greetings(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Map<String, Object> model) {

		model.put("name", name);

		return "greeting";
	}

	@GetMapping
	public String main(Map<String, Object> model) {

		Iterable<Message> messages = messageRepo.findAll();
		model.put("messages", messages);

		return "main";
	}

	@PostMapping
	public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {

		Message message = new Message(text, tag);
		messageRepo.save(message);

		Iterable<Message> messages = messageRepo.findAll();
		model.put("messages", messages);

		return "main";
	}
}
