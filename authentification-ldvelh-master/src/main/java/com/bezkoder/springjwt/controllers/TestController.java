package com.bezkoder.springjwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/investisseur")
	@PreAuthorize("hasRole('INVESTISSEUR') or hasRole('ENTREPRENEUR') or hasRole('ADMIN')")
	public String userAccess() {
		return "INVESTISSEUR Content.";
	}

	@GetMapping("/entrepreneur")
	@PreAuthorize("hasRole('ENTREPRENEUR')")
	public String moderatorAccess() {
		return "ENTREPRENEUR Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
