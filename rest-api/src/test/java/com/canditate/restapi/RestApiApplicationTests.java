package com.canditate.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.canditate.restapi.controller.CandidateController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestApiApplicationTests {
	
	@Autowired
	private CandidateController controller;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
