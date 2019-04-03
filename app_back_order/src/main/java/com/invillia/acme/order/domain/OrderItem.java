package com.invillia.acme.order.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Domain que representa um item
 * 
 * @author erick.oliveira
 *
 */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem extends EntityBase {


	@ManyToOne(targetEntity=Order.class)
    private Order order;

    @NotEmpty
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @DecimalMin("0.00")
    @Column(nullable = false)
    private BigDecimal valor;

    @Min(1)
    @NotNull
    @Column(nullable = false)
    private Integer quantidade;
}