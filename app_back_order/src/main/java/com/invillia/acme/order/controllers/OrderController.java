package com.invillia.acme.order.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.order.domain.Order;
import com.invillia.acme.order.dto.OrderDTO;
import com.invillia.acme.order.enums.OrderStatusEnum;
import com.invillia.acme.order.service.OrderService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * Controller do microservice de Order
 * 
 * @author erick.oliveira
 *
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

	private OrderService orderService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/find")
	@ApiOperation(notes = "Recuperar uma Order por parametros", value = "Filter", response = ResponseEntity.class)
    @Secured("ROLE_USER")
	public ResponseEntity<List<OrderDTO>> findByParameters(@RequestParam(required = false) String endereco,
			@RequestParam(required = false) OrderStatusEnum status,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime confirmacao) {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrder(endereco, status, confirmacao).stream()
				.map(this::toDTO).collect(Collectors.toList()));
	}

	/**
	 * Metodo responsavel por salvar uma Order
	 * 
	 * @param OrderDTO
	 * @return ResponseEntity
	 */
	@PostMapping("/save")
	@ApiOperation(notes = "Salvar os dados de uma Order", value = "Order", response = ResponseEntity.class)
    @Secured("ROLE_USER")
	public ResponseEntity<OrderDTO> create(@RequestBody @Valid OrderDTO dto) {
		Order order = modelMapper.map(dto, Order.class);
		order = orderService.save(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(order, OrderDTO.class));
	}

	public OrderDTO toDTO(Order order) {
		if (order == null) {
			return null;
		} else {
			OrderDTO orderDTO = OrderDTO.builder().build();
			orderDTO = modelMapper.map(order, OrderDTO.class);
			return orderDTO;
		}
	}

}