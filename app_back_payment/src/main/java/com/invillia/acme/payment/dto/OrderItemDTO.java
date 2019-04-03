    
package com.invillia.acme.payment.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;


/**
 * DTO do ItemOrder
 * @author erick.oliveira
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDTO {

    private Long id;

    @NotEmpty
    private String descricao;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal valor;

    @Min(1)
    @NotNull
    private Integer quantidade;

}