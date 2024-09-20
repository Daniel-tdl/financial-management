package br.com.jmt.financial_management.domain.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HttpErrorResponseWrapper {
    protected LocalDateTime timestamp;
    protected Integer status;
    protected String commonError;
    protected String message;
    protected String path;
    protected Collection<String> errorsList;

    public HttpErrorResponseWrapper(HttpStatus httpStatus, String message, String path, Collection<String> errors) {
        this.status = httpStatus.value();
        this.commonError = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
        this.errorsList = errors;
        this.timestamp = LocalDateTime.now();
    }

    public HttpErrorResponseWrapper(HttpStatus httpStatus, String message, String path) {
        this.status = httpStatus.value();
        this.commonError = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
