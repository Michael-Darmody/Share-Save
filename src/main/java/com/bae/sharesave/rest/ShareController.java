package com.bae.sharesave.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("/getShares")
	public List<Share> getShares() {
		return this.service.getShares();
	}

	@PutMapping("/update/{id}")
	public Share updateShare(@PathVariable Long id, @RequestBody Share newShare) {
		return this.service.updateShare(id, newShare);
	}

	@DeleteMapping("/delete/{id}")
	public boolean deleteShare(@PathVariable Long id) {
		return this.service.deleteShare(id);
	}
}
