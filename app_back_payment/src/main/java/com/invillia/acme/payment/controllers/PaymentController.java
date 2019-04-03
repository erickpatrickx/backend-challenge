package com.invillia.acme.payment.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invillia.acme.payment.domain.Payment;
import com.invillia.acme.payment.dto.PaymentDTO;
import com.invillia.acme.payment.service.PaymentService;

import io.swagger.annotations.ApiOperation;

/**
 * Controle de Payment
 * @author erick.oliveira
 *
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    
	@Autowired
	private ModelMapper modelMapper;

    
    @Autowired
    public PaymentController(PaymentService paymentService) {
    	this.paymentService = paymentService;
    }

    /**
     * Metodo de pagamento de uma order
     * @param dto
     * @return
     */
    @PostMapping("/pay")
	@ApiOperation(notes = "Realiza o pagamento de uma Order", value = "Pay", response = ResponseEntity.class)
    @Secured("ROLE_USER")
    public ResponseEntity<?> realizarPagamento(@RequestBody @Valid PaymentDTO dto,HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.realizarPagamento(modelMapper.map(dto, Payment.class),request));
    }

}