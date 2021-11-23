package com.esdo.bepilot.Specification;

import com.esdo.bepilot.Model.Entity.Customer;
import com.esdo.bepilot.Model.Entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class UserSpecification {
    public static Specification<User> filterUser(String keyWord){
        return (root, query, criteriaBuilder) -> {
            Collection<Predicate> predicates = new ArrayList<>();
            if(! Objects.isNull(keyWord)){
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + keyWord + "%" ));
                predicates.add(criteriaBuilder.like(root.get("phone"),"%" + keyWord + "%"));
                predicates.add(criteriaBuilder.like(root.get("userKey"),"%" + keyWord + "%"));

            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
