package br.com.jmt.financial_management.domain.model.dto;

import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AccountDTO {

    private Long id;

    @JsonProperty("data_vencimento")
    private LocalDate dateVenc;

    @JsonProperty("data_pagamento")
    private LocalDate datePayment;

    @JsonProperty("valor")
    private Double amount;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("situacao")
    private AccountStatusEnum status;
}
