package com.zubentsov.chirpchirp.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zubentsov.chirpchirp.domen.Role;
import com.zubentsov.chirpchirp.domen.User;
import com.zubentsov.chirpchirp.repos.UserRepo;

@Controller
public class RegistrationController {

	UserRepo userRepo;

	RegistrationController(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

	@PostMapping("/registration")
	public String addUser(User user, Map<String, Object> model) {
		User userFromDb = userRepo.findByUsername(user.getUsername());
		
		if (userFromDb != null) {
			model.put("message", "User already exists!");
			return "registration";
		}
		
		user.setActive(true);
		user.setRoles(Collections.singleton(Role.USER));
		userRepo.save(user);

		return "redirect:/login";
	}

}
