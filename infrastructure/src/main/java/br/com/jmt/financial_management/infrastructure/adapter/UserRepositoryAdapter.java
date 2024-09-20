package br.com.jmt.financial_management.infrastructure.adapter;

import br.com.jmt.financial_management.domain.entity.UserEntity;
import br.com.jmt.financial_management.domain.ports.out.UserPort;
import br.com.jmt.financial_management.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserPort {

    private final UserRepository repository;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
