package br.com.jmt.financial_management.infrastructure.repository;

import br.com.jmt.financial_management.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
  Optional<UserEntity> findByUsername(String username);
}
