package com.invillia.acme.order.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.invillia.acme.order.enums.OrderStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Domain que representa uma Order
 * 
 * @author erick.oliveira
 *
 */
@Entity
@Table(name="Orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order  extends EntityBase{


    @NotEmpty
    @Column(nullable = false)
    private String endereco;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime confirmacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatusEnum status;

    @Size(min = 1)
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> items;

}