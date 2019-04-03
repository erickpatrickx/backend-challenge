package com.invillia.acme.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.invillia.acme.order.domain.Order;


/**
 * Repositorio do Order
 * @author erick.oliveira
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor {
}