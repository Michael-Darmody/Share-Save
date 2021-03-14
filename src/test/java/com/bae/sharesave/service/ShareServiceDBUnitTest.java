package com.bae.sharesave.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

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

	@Test
	public void testUpdateShare() {
		// Arguments for get method and expected results
		Long id = 1L;
		Share newShare = new Share("Vodafone", 200, 1.5);
		Optional<Share> optionalShare = Optional.of(new Share(id, "Barclays", 1000, 2));
		Share updatedShare = new Share(id, newShare.getName(), newShare.getAmount(), newShare.getPrice());

		// Telling mocked repository what to do
		Mockito.when(this.repo.findById(id)).thenReturn(optionalShare);
		Mockito.when(this.repo.save(updatedShare)).thenReturn(updatedShare);

		// Test
		assertThat(this.service.updateShare(id, newShare)).isEqualTo(updatedShare);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedShare);
	}

	@Test
	public void testDeleteShare() {
		Long id = 1L;

		// Telling mocked repository what to do
		Mockito.when(this.repo.existsById(id)).thenReturn(false);

		// Test
		assertThat(this.service.deleteShare(id)).isTrue();
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
}
