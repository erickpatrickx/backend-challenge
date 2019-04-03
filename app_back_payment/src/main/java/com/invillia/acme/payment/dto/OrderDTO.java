package com.invillia.acme.payment.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.invillia.acme.payment.enums.OrderStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO do Order
 * @author erick.oliveira
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    private Long id;

    private String endereco;

    private OrderStatusEnum status;
    
    private LocalDateTime confirmacao;
    
    private List<OrderItemDTO> items;
}