package com.esdo.bepilot.Specification;

import com.esdo.bepilot.Model.Entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class CustomerSpecification {

    public static Specification<Customer> filterCustomer(String keyWord){
        return (root, query, criteriaBuilder) -> {
            Collection<Predicate> predicates = new ArrayList<>();
            if(! Objects.isNull(keyWord)){
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + keyWord + "%" ));
                predicates.add(criteriaBuilder.like(root.get("phone"),"%" + keyWord + "%"));
                predicates.add(criteriaBuilder.like(root.get("customerKey"),"%" + keyWord + "%"));

            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
