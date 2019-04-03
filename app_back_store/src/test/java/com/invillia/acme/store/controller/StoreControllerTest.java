package com.invillia.acme.store.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.store.dto.StoreDTO;

/**
 * Teste para controllers do Store
 * 
 * @author erick.oliveira
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StoreControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	//TODO Token gerado ponto de melhoria nos testes unitarios
	String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjoiUk9MRV9BRE1JTiIsImV4cCI6MTU1NTE3MTExNH0.-0D3T-JWRFss8Y8G5eEUfwZk4xQbrm3mjIaQJBfu_q2NGbpZ56mZQAKCBmwL4oDKj7pxIEqpAaJYnvlyxRZtJg";

	
	
	@Test
	public void deveSalvarUmaStore() throws Exception {
		StoreDTO storeDTO =  StoreDTO.builder().nome("Store - Test").endereco("endereco").build();
		mockMvc.perform(post("/stores/save").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token)
				.content(objectMapper.writeValueAsString(storeDTO)).header("Content-Type", "application/json"))
				.andExpect(status().isCreated());
	}
	
	
	@Test
	public void deveApresentarErroItemsVazio() throws Exception {
		StoreDTO storeDTO =  StoreDTO.builder().build();
		mockMvc.perform(post("/stores/save").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token)
				.content(objectMapper.writeValueAsString(storeDTO)).header("Content-Type", "application/json"))
				.andExpect(status().isInternalServerError());
	}
	
	@Test
	public void deveListarStores() throws Exception {
		mockMvc.perform(get("/stores/find").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token)
				.header("Content-Type", "application/json"))
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void deveBloquearUsuarioNaoAutorizado() throws Exception {
		StoreDTO storeDTO =  StoreDTO.builder().nome("Store - Test").endereco("endereco").build();
		mockMvc.perform(post("/stores/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(storeDTO)).header("Content-Type", "application/json"))
				.andExpect(status().isUnauthorized());
	}

}