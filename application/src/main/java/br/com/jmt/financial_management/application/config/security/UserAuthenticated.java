package br.com.jmt.financial_management.application.config.security;

import br.com.jmt.financial_management.domain.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserAuthenticated implements UserDetails {
  private final UserEntity userEntity;

  public UserAuthenticated(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  @Override
  public String getUsername() {
    return userEntity.getUsername();
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(() -> "read");
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
