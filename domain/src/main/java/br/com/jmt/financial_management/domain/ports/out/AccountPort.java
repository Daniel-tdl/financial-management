package br.com.jmt.financial_management.domain.ports.out;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface AccountPort {
    Optional<AccountEntity> findById(Long id);
    AccountEntity save(AccountEntity entity);
    Page<AccountEntity> findAll(Specification<AccountEntity> spec, Pageable pageable);
    void saveAll(List<AccountEntity> entities);
}

