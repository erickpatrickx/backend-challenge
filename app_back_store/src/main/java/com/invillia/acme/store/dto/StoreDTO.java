package com.invillia.acme.store.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO do Store
 * @author erick.oliveira
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreDTO {

    private Long id;
	
	@NotEmpty(message = "Nome não informado")
    @Size(min=3,max=100,message="Campo Nome: Limite de caracteres excedido - minimo 3 e máximo 250")
    private String nome;
    
	@NotEmpty(message = "Endereço não informado")
    @Size(max=250)
	private String endereco;
    
}
    
    
    

    
