package com.invillia.acme.order.repository.specifications;

import java.time.LocalDateTime;

import javax.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;

import com.invillia.acme.order.domain.Order;
import com.invillia.acme.order.enums.OrderStatusEnum;

public class OrderSpecification {

	private OrderSpecification() {
	}

	
	public static Specification<Order> idEquals(Long id) {
		return (root, cq, cb) -> id == null ? cb.isTrue(cb.literal(true))
				: cb.equal(root.get("id"), id);
	}
	
	public static Specification<Order> enderecoContains(String endereco) {
		return (root, cq, cb) -> endereco == null ? cb.isTrue(cb.literal(true))
				: cb.like(cb.lower(root.get("endereco")), endereco.toLowerCase() + "%");
	}

	public static Specification<Order> statusEqualsTo(OrderStatusEnum status) {
		return (root, cq, cb) -> status == null ? cb.isTrue(cb.literal(true)) : cb.equal(root.get("status"), status);
	}

	public static Specification<Order> confirmationDateEqualsTo(LocalDateTime confirmacao) {
		return (root, cq, cb) -> confirmacao == null ? cb.isTrue(cb.literal(true))
				: cb.equal(root.get("confirmacao"), confirmacao);
	}

	public static Specification<Order> fetchItems() {
		return (root, cq, cb) -> {
			root.fetch("items", JoinType.INNER);
			return null;
		};
	}

}