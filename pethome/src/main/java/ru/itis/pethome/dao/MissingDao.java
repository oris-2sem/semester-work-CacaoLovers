package ru.itis.pethome.dao;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itis.pethome.model.Account;
import ru.itis.pethome.model.District;
import ru.itis.pethome.model.Missing;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MissingDao extends JpaRepository<Missing, UUID>, JpaSpecificationExecutor<Missing> {
    List<Missing> findMissingByOwner(Account account);

    default List<Missing> getMissingByParameters(Map<String, String> parameters) {

        Specification<Missing> specification = (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();


            if (parameters.containsKey("status")) {
                Missing.Status status =  Missing.Status.valueOf(parameters.get("status"));
                if (status != null) {
                    predicates.add(criteriaBuilder.equal(root.get("status"), status));
                }
            }

            if (parameters.containsKey("type")) {
                Missing.Type type =  Missing.Type.valueOf(parameters.get("type"));
                if (type != null) {
                    predicates.add(criteriaBuilder.equal(root.get("type"), type));
                }
            }


            if (parameters.containsKey("district")) {
                UUID district = UUID.fromString(parameters.get("district"));
                Join<Missing, District> join = root.join("district_id", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(join.get("id"), district));
            }

            if (parameters.containsKey("owner")) {
                UUID owner = UUID.fromString(parameters.get("owner"));
                Join<Missing, Account> join = root.join("owner_id", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(join.get("id"), owner));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return findAll(specification);
    }
}
