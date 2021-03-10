package com.bae.sharesave.service;

import java.util.List;

import com.bae.sharesave.domain.Share;

public interface ShareService {
	Share createShare(Share share);

	List<Share> getShares();

	Share updateShare(Long id, Share newShare);
}
