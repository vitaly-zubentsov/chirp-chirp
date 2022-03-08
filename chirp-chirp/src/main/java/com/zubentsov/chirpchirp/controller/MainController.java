package com.zubentsov.chirpchirp.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zubentsov.chirpchirp.domen.Message;
import com.zubentsov.chirpchirp.domen.User;
import com.zubentsov.chirpchirp.repos.MessageRepo;

@Controller
public class MainController {

	MessageRepo messageRepo;

	MainController(MessageRepo messageRepo) {
		this.messageRepo = messageRepo;
	}

	@GetMapping("/")
	public String greetings(Map<String, Object> model) {

		return "greeting";
	}

	@GetMapping("/main")
	public String main(Map<String, Object> model) {

		Iterable<Message> messages = messageRepo.findAll();
		model.put("messages", messages);

		return "main";
	}

	@PostMapping("/main")
	public String add(
			@AuthenticationPrincipal User user,
			@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {

		Message message = new Message(text, tag, user);
		messageRepo.save(message);

		Iterable<Message> messages = messageRepo.findAll();
		model.put("messages", messages);

		return "main";
	}

	@PostMapping("/filter")
	public String findByTag(@RequestParam String filter, Map<String, Object> model) {

		Iterable<Message> messages;

		if ((filter != null) && !(filter.isEmpty())) {
			messages = messageRepo.findByTag(filter);
		} else {
			messages = messageRepo.findAll();
		}

		model.put("messages", messages);

		return "main";
	}
}
