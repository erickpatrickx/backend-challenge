package com.invillia.acme.order.controller;

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

	String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGllbnRlIiwicm9sZXMiOiJST0xFX1VTRVIiLCJleHAiOjE1NTUwODk3NTN9.HLs4NFH19s3a85TompEtAtgIvY3K4gr4jka30y3kWWgCOIoEVPo-c8BeISnPbdbvtKukaf0kzeFHPh511mCU4Q";

	
	
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