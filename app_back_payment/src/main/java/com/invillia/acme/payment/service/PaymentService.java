package com.invillia.acme.payment.service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invillia.acme.payment.domain.Payment;
import com.invillia.acme.payment.dto.OrderDTO;
import com.invillia.acme.payment.dto.OrderItemDTO;
import com.invillia.acme.payment.enums.OrderStatusEnum;
import com.invillia.acme.payment.enums.PaymentStatusEnum;
import com.invillia.acme.payment.exception.BusinessException;
import com.invillia.acme.payment.repository.PaymentRepository;
import com.invillia.acme.payment.security.TokenAuthenticationService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

/**
 * Classe de serviço de Payment
 * @author erick.oliveira
 *
 */
@Service
public class PaymentService{

    @Value("${gateway.url}")
    private String gateway;
    
	private ObjectMapper objectMapper;
	
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,ObjectMapper objectMapper) {
    	this.paymentRepository = paymentRepository;
    	this.objectMapper = objectMapper;

    }

    /**
     * Metodo de realizar pagamento
     * @param payment
     * @param request 
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public Payment realizarPagamento(Payment payment, HttpServletRequest request) {
		try {
			JSONObject orderJson = Unirest.get(gateway + "/orders/find/" + payment.getIdOrder())
					.header(TokenAuthenticationService.HEADER_STRING, request.getHeader("Authorization")).asJson()
					.getBody().getObject();
			OrderDTO order = objectMapper.readValue(orderJson.toString(), OrderDTO.class);

			if (order == null) {
				throw new BusinessException("Não existe este Pedido");
			}

			if (paymentRepository.findAllByIdOrder(payment.getIdOrder()).stream().collect(Collectors.toList())
					.size() > 0) {
				throw new BusinessException("Já existe pagamento para este Pedido");
			}
			payment.setDataConfirmacao(order.getConfirmacao());
			payment.setValorTotal(order.getItems().stream().map(OrderItemDTO::getValor).reduce(BigDecimal::add).get());
			;

			HttpResponse<JsonNode> response = Unirest
					.put(gateway + "/orders/update/" + payment.getIdOrder() + "/" + OrderStatusEnum.PAGAMENTO_REALIZADO)
					.header(TokenAuthenticationService.HEADER_STRING, request.getHeader("Authorization")).asJson();
			if (!HttpStatus.OK.equals(response.getStatus())) {
				throw new BusinessException("Ocorreu um erro ao atulizar o staus do pedido");
			}
			payment.setStatusPagamento(PaymentStatusEnum.PROCESSADO);
			paymentRepository.save(payment);

		} catch (Exception e) {
			throw new BusinessException("Ocorreu um erro ao realizar o pagamento");
		}

		return payment;
    }

}
