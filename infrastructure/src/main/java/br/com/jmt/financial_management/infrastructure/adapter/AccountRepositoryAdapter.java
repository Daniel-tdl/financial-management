package br.com.jmt.financial_management.infrastructure.adapter;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.ports.out.AccountPort;
import br.com.jmt.financial_management.infrastructure.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountPort {

    private final AccountRepository repository;

    @Override
    public Optional<AccountEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public AccountEntity save(AccountEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Page<AccountEntity> findAll(Specification<AccountEntity> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    @Override
    public void saveAll(List<AccountEntity> entities) {
        repository.saveAll(entities);
    }
}
