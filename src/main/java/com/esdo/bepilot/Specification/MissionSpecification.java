package com.esdo.bepilot.Specification;

import com.esdo.bepilot.Model.Entity.Customer;
import com.esdo.bepilot.Model.Entity.Mission;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public class MissionSpecification {
    public static Specification<Mission> filterMission(String name, String communication) {
        return (root, query, criteriaBuilder) -> {
            Collection<Predicate> predicates = new ArrayList<>();
            if (!Objects.isNull(name)) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (!Objects.isNull(communication)) {
                predicates.add(criteriaBuilder.equal(root.get("communication"), communication));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Mission> filterForMission(String status, Long customerId) {
        return (root, query, criteriaBuilder) -> {
            Collection<Predicate> predicates = new ArrayList<>();

            if (!Objects.isNull(customerId)) {
                Path<Customer> cryptoPath = root.get("customer");
                predicates.add(criteriaBuilder.equal(cryptoPath.<Long>get("id"), customerId));
            }
            if (!Objects.isNull(status)) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
