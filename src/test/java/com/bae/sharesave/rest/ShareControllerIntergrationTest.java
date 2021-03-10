package com.bae.sharesave.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.sharesave.domain.Share;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:share-schema.sql",
		"classpath:share-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class ShareControllerIntergrationTest {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Share share = new Share("Vodafone", 2000, 1.5);
		Share savedShare = new Share(2L, "Vodafone", 2000, 1.5);

		// Mock request
		String shareJSON = this.mapper.writeValueAsString(share);
		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(shareJSON);

		// Mock response
		String savedShareJSON = this.mapper.writeValueAsString(savedShare);
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(savedShareJSON);

		// Test
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

	@Test
	void testRead() throws Exception {
		Share share = new Share(1L, "Barclays", 1000, 2);

		List<Share> sharesList = List.of(share);
		String sharesAsJSON = this.mapper.writeValueAsString(sharesList);

		// Mock request
		RequestBuilder mockRequest = get("/getShares");

		// Mock response
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(sharesAsJSON);

		// Test
		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdate() throws Exception {
		Share updatedShare = new Share("Tesla", 100, 2000);
		Share savedShare = new Share(1L, "Tesla", 100, 2000);

		// Mock request
		String updatedShareJSON = this.mapper.writeValueAsString(updatedShare);
		RequestBuilder mockRequest = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(updatedShareJSON);

		// Mock response
		String savedShareJSON = this.mapper.writeValueAsString(savedShare);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(savedShareJSON);

		// Test
		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		// Mock request
		RequestBuilder mockRequest = delete("/delete/1");

		// Mock response
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("true");

		// Test
		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
}
