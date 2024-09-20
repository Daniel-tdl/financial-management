package br.com.jmt.financial_management.domain.exceptions;

public class ErrorAccountException extends RuntimeException{
    public ErrorAccountException(String description){super(description);}
}
