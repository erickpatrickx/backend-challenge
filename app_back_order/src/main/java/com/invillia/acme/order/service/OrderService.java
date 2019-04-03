package com.invillia.acme.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.invillia.acme.order.domain.Order;
import com.invillia.acme.order.enums.OrderStatusEnum;
import com.invillia.acme.order.repository.OrderRepository;
import com.invillia.acme.order.repository.specifications.OrderSpecification;

/**
 * Servicos do Order
 * 
 * @author erick.oliveira
 *
 */
@Service
public class OrderService {
	
	@Autowired
    private  OrderRepository repository;

    /**
     * Buscar uma Order
     * @param address
     * @param status
     * @param confirmationDate
     * @return List<Order>
     */
    public List<Order> findOrder(String endereco, OrderStatusEnum status, LocalDateTime confirmacao) {
    	  Specification<Order> spec = Specification
                  .where(OrderSpecification.enderecoContains(endereco))
                  .and(OrderSpecification.statusEqualsTo(status))
                  .and(OrderSpecification.confirmationDateEqualsTo(confirmacao))
                  .and(OrderSpecification.fetchItems());
          return repository.findAll(spec);
    }
	
    /**
     * Salvar uma Order
     * @param order
     * @return
     */
    public Order save(Order order) {
		order.getItems().forEach(item -> item.setOrder(order));
        return repository.save(order);
    }


}