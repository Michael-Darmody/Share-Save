package com.bae.sharesave.service;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Share updateShare(Long id, Share newShare) {
		Optional<Share> existingOptional = this.repo.findById(id);
		Share existing = existingOptional.get();
		existing.setName(newShare.getName());
		existing.setAmount(newShare.getAmount());
		existing.setPrice(newShare.getPrice());
		return this.repo.save(existing);
	}

	@Override
	public boolean deleteShare(Long id) {
		this.repo.deleteById(id);
		boolean exists = this.repo.existsById(id);
		return !exists;
	}

}
