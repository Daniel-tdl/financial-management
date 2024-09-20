package br.com.jmt.financial_management.domain.model.request;

import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRequest {

    @JsonProperty("data_vencimento")
    @NotNull(message = "Informe a data de inicio.")
    private LocalDate dateVenc;

    @NotNull(message = "Informe a data de pagamento.")
    @JsonProperty("data_pagamento")
    private LocalDate datePayment;

    @NotNull(message = "Informe o valor.")
    @JsonProperty("valor")
    private Double amount;

    @NotEmpty(message = "Informe a descrição.")
    @JsonProperty("descricao")
    private String description;

    @NotNull(message = "Informe a situção (PAID ou PENDING).")
    @JsonProperty("situacao")
    private AccountStatusEnum status;
}
