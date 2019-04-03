package com.invillia.acme.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.invillia.acme.order.enums.OrderStatusEnum;

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

    @NotEmpty
    private String endereco;

    @NotNull
    private LocalDateTime confirmacao;

    @NotNull
    private OrderStatusEnum status;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<OrderItemDTO> items;
}