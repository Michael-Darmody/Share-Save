package com.bae.sharesave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bae.sharesave.domain.Share;

@Repository
public interface ShareRepo extends JpaRepository<Share, Long> {

}
