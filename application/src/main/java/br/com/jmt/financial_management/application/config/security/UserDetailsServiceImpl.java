package br.com.jmt.financial_management.application.config.security;

import br.com.jmt.financial_management.domain.ports.out.UserPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserPort userPort;

  public UserDetailsServiceImpl(UserPort userPort) {
    this.userPort = userPort;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userPort.findByUsername(username)
            .map(UserAuthenticated::new)
        .orElseThrow(
            () -> new UsernameNotFoundException("UserEntity Not Found with username: " + username));
  }

}
