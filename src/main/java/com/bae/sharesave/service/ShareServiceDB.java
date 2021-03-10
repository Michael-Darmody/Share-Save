package com.bae.sharesave.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bae.sharesave.domain.Share;
import com.bae.sharesave.repo.ShareRepo;

@Service
public class ShareServiceDB implements ShareService {

	private ShareRepo repo;

	public ShareServiceDB(ShareRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Share createShare(Share share) {
		return this.repo.save(share);
	}

	@Override
	public List<Share> getShares() {
		return this.repo.findAll();
	}

}
