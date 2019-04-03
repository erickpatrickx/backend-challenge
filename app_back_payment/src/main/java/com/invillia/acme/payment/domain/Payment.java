package com.invillia.acme.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.invillia.acme.payment.enums.PaymentStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment extends EntityBase {


    @NonNull
    private String numeroCartao;

    @NonNull
    @Column(nullable = false)
    private Long idOrder;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatusEnum statusPagamento;

    private LocalDateTime dataConfirmacao;

    private BigDecimal valorTotal;

    private BigDecimal valorReembolso;
    
    
}
