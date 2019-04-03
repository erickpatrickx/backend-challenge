package com.invillia.acme.store.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Domain que representa uma Store
 * 
 * @author erick.oliveira
 *
 */
@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Store extends EntityBase {

	@Size(min = 3, max = 250)
	@NotNull
	@Column(nullable = false, unique = true)
	private String nome;

	@Size(max = 250)
	@NotNull
	@Column(nullable = false)
	private String endereco;

}
