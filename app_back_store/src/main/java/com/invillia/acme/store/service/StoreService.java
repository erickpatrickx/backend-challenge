package com.invillia.acme.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.invillia.acme.store.domain.Store;
import com.invillia.acme.store.dto.FilterStoreDTO;
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
	 * @return
	 */
	public List<Store> findStore(FilterStoreDTO filter) {
		 Specification<Store> spec = Specification
	                .where(StoreSpecification.nomeContains(filter.getNome()))
	                .and(StoreSpecification.enderecoContains(filter.getEndereco()));
		return repository.findAll(spec);
	}

	/**
	 * Metodo de salvar uma Store
	 * 
	 * @param store
	 * @return
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
	 * @return
	 */
	public Store update(Store store) {
		repository.save(store);
		return store;
	}

}
