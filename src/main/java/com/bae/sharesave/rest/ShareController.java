package com.bae.sharesave.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.sharesave.domain.Share;
import com.bae.sharesave.service.ShareService;

@RestController
public class ShareController {

	private ShareService service;

	public ShareController(ShareService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public Share createShare(@RequestBody Share share) {
		return this.service.createShare(share);
	}
}