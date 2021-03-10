package com.bae.sharesave.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bae.sharesave.domain.Share;
import com.bae.sharesave.repo.ShareRepo;

@SpringBootTest
public class ShareServiceDBUnitTest {

	@Autowired
	private ShareServiceDB service;

	@MockBean
	private ShareRepo repo;

	@Test
	public void testCreateShare() {
		// Arguments for create method and expected results
		Share newShare = new Share("Vodafone", 2000, 1.5);
		Share savedShare = new Share(1L, "Vodafone", 2000, 1.5);

		// Telling mocked repository what to do
		Mockito.when(this.repo.save(newShare)).thenReturn(savedShare);

		// Test
		assertThat(this.service.createShare(newShare)).isEqualTo(savedShare);

		Mockito.verify(this.repo, Mockito.times(1)).save(newShare);
	}

	@Test
	public void testGetPeople() {
		// Arguments for get method and expected results
		Share savedShare = new Share(1L, "Vodafone", 2000, 1.5);
		List<Share> sharesList = List.of(savedShare);

		// Telling mocked repository what to do
		Mockito.when(this.repo.findAll()).thenReturn(sharesList);

		// Test
		assertThat(this.service.getShares().equals(sharesList));
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
}
