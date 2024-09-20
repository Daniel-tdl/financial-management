package br.com.jmt.financial_management.domain.exceptions;

public class DataInvalidException extends RuntimeException{
    public DataInvalidException(String description){super(description);}
}
