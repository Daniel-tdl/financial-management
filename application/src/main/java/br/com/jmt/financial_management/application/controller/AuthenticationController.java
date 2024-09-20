package br.com.jmt.financial_management.application.controller;

import br.com.jmt.financial_management.application.config.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("/authenticate")
  public String authenticate(Authentication authentication) {
    return authenticationService.authenticate(authentication);
  }
}
