package br.com.jmt.financial_management.domain.ports.out;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface UserPort {
    Optional<UserEntity> findByUsername(String username);
}

