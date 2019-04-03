package com.invillia.acme.order.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.order.dto.OrderDTO;
import com.invillia.acme.order.dto.OrderItemDTO;
import com.invillia.acme.order.enums.OrderStatusEnum;

/**
 * Teste para controllers do Order
 * 
 * @author erick.oliveira
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	//TODO Está geração de token deve ser analisada com mais calma.
	String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGllbnRlIiwicm9sZXMiOiJST0xFX1VTRVIiLCJleHAiOjE1NTUwODk3NTN9.HLs4NFH19s3a85TompEtAtgIvY3K4gr4jka30y3kWWgCOIoEVPo-c8BeISnPbdbvtKukaf0kzeFHPh511mCU4Q";

	@Test
	public void deveSalvarUmaOrder() throws Exception {
		OrderDTO orderDTO =  OrderDTO.builder().endereco("endereco").confirmacao(LocalDateTime.now()).status(OrderStatusEnum.CRIADO).build();
		OrderItemDTO orderItemDTO = OrderItemDTO.builder().descricao("item").quantidade(1).valor(new BigDecimal(10.00)).build();
		List<OrderItemDTO> items = new ArrayList<OrderItemDTO>();
		items.add(orderItemDTO);
		orderDTO.setItems(items);
	
		mockMvc.perform(post("/orders/save").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token)
				.content(objectMapper.writeValueAsString(orderDTO)).header("Content-Type", "application/json"))
				.andExpect(status().isCreated());
	}
	
	
	@Test
	public void deveApresentarErroItemsVazio() throws Exception {
		OrderDTO orderDTO =  OrderDTO.builder().endereco("endereco").confirmacao(LocalDateTime.now()).status(OrderStatusEnum.CRIADO).build();
		mockMvc.perform(post("/orders/save").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token)
				.content(objectMapper.writeValueAsString(orderDTO)).header("Content-Type", "application/json"))
				.andExpect(status().isInternalServerError());
	}
	
	@Test
	public void deveListarOrders() throws Exception {
		mockMvc.perform(get("/orders/find").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", token)
				.header("Content-Type", "application/json"))
				.andExpect(status().isOk()).andReturn();
	}
	
	
	@Test
	public void deveBloquearUsuarioNaoAutorizado() throws Exception {
		OrderDTO orderDTO =  OrderDTO.builder().endereco("endereco").confirmacao(LocalDateTime.now()).status(OrderStatusEnum.CRIADO).build();
		mockMvc.perform(post("/orders/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(orderDTO)).header("Content-Type", "application/json"))
				.andExpect(status().isUnauthorized());
	}

}