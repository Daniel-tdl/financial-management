package br.com.jmt.financial_management.domain.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String description){super(description);}
}
