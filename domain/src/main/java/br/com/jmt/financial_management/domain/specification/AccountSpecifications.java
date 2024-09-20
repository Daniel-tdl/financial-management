package br.com.jmt.financial_management.domain.specification;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import br.com.jmt.financial_management.domain.model.request.AccountPayableRequest;
import br.com.jmt.financial_management.domain.model.request.AccountTotalAmountRequest;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class AccountSpecifications {

    private static final String DATA_VENC = "dateVenc";
    private static final String DESCRIPTION = "description";
    private static final String DATA_UPDATE = "updatedAt";
    private static final String STATUS = "status";

    private AccountSpecifications() {}


    public static Specification<AccountEntity> accountPayableSpecification(AccountPayableRequest request) {

        return (root, query, builder) -> {
            Predicate p = builder.conjunction();
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(request.getDescription()))
                predicates.add(builder.like(builder.lower(root.get(DESCRIPTION)), "%" + request.getDescription().toLowerCase() + "%"));

            if (Objects.nonNull(request.getDateVenc()))
                predicates.add(builder.lessThanOrEqualTo(root.get(DATA_VENC), request.getDateVenc()));

            query.orderBy(builder.desc(root.get(DATA_VENC)));

            predicates.forEach(builder::and);
            p = builder.and(predicates.toArray(new Predicate[0]));

            return p;
        };

    }

    public static Specification<AccountEntity> accountTotalAmountSpecification(AccountTotalAmountRequest request) {

        return (root, query, builder) -> {
            Predicate p = builder.conjunction();
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.equal(root.get(STATUS), AccountStatusEnum.PAID));

            var initialDateAux = Objects.nonNull(request.getDateStart()) ? request.getDateStart().atTime(0, 0, 0) : LocalDateTime.now();
            var finalDateAux = Objects.nonNull(request.getDateEnd()) ? request.getDateEnd().atTime(23, 59, 59) : LocalDateTime.now();
            predicates.add(builder.between(root.get(DATA_UPDATE), initialDateAux, finalDateAux));

            query.orderBy(builder.desc(root.get(DATA_UPDATE)));

            predicates.forEach(builder::and);
            p = builder.and(predicates.toArray(new Predicate[0]));

            return p;
        };
    }
}
