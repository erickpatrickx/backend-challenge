package com.invillia.acme.store.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidade base do projeto
 * @author erick.oliveira
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityBase {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    
    @Override
    public boolean equals(Object obj) {
        if (id == null) {
            throw new RuntimeException("Entity "+this.getClass().getSimpleName()+": id is null.");
        }

        return obj !=null && (super.equals(obj)
            || (this.getClass().equals(obj.getClass()) && this.id.equals(((EntityBase) obj).getId())));
    }
}
