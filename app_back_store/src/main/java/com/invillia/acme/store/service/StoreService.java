package com.invillia.acme.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.invillia.acme.store.domain.Store;
import com.invillia.acme.store.exception.BadRequestException;
import com.invillia.acme.store.repository.StoreRepository;
import com.invillia.acme.store.repository.specifications.StoreSpecification;

/**
 * Servicos do Store
 * 
 * @author erick.oliveira
 *
 */
@Service
public class StoreService {

	@Autowired
	StoreRepository repository;

	/**
	 * Metodo de buscar uma Store
	 * 
	 * @param filter
	 * @return List<Store>
	 */
	public List<Store> findStore(String nome , String endereco) {
		 Specification<Store> spec = Specification
	                .where(StoreSpecification.nomeContains(nome))
	                .and(StoreSpecification.enderecoContains(endereco));
		return repository.findAll(spec);
	}

	/**
	 * Metodo de salvar uma Store
	 * 
	 * @param store
	 * @return Store
	 */
	public Store save(Store store) {
		repository.save(store);
		return store;
	}

	/**
	 * Metodo de atualizar uma Store
	 * 
	 * @param store
	 * @param id
	 * @return Store
	 */
	public Store update(Store store,Long id) {
		if(!repository.findById(id).isPresent()) {
			throw new BadRequestException("Id informado não encontrado");
		}
		store.setId(id);
		repository.save(store);
		return store;
	}

}
