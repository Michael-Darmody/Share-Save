package com.bae.sharesave.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
