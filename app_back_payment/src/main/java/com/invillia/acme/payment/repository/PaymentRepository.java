package com.invillia.acme.payment.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.invillia.acme.payment.domain.Payment;


/**
 * Repositorio do Store
 * @author erick.oliveira
 *
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>, JpaSpecificationExecutor {

	Collection<Payment> findAllByIdOrder(Long idOrder);
	
}
