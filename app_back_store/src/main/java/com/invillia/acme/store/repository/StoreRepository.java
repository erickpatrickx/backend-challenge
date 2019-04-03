package com.invillia.acme.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.invillia.acme.store.domain.Store;


/**
 * Repositorio do Store
 * @author erick.oliveira
 *
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, JpaSpecificationExecutor {
	
}
