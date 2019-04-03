package com.invillia.acme.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * DTO de filtro do Store
 * @author erick.oliveira
 *
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class FilterStoreDTO {

    private String nome;
    
	private String endereco;
    
}
    
    
    

    
