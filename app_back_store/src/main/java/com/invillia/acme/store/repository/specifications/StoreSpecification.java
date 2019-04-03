    
package com.invillia.acme.store.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.invillia.acme.store.domain.Store;

public class StoreSpecification {

    private StoreSpecification() {
    }

    public static Specification<Store> nomeContains(String name) {
        return (root, cq, cb) ->
                name == null ?
                        cb.isTrue(cb.literal(true)) :
                        cb.like(cb.lower(root.get("nome")), name.toLowerCase() + "%");
    }

    public static Specification<Store> enderecoContains(String address) {
        return (root, cq, cb) ->
                address == null ?
                        cb.isTrue(cb.literal(true)) :
                        cb.like(cb.lower(root.get("endereco")), address.toLowerCase() + "%");
    }
}