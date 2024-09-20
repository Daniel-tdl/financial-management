package br.com.jmt.financial_management.domain.model.dto;

import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountPayableDTO {

    private static final String DATETIME_JSON_PATTERN = "dd/MM/yyyy";

    private Pagination pagination;
    private List<Content> contents;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @With
    @Builder
    public static class Pagination {
        private Integer page;
        private Integer totalPages;
        private Long sizeItems;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @With
    public static class Content {

        private Long id;

        @JsonFormat(pattern = DATETIME_JSON_PATTERN)
        @JsonProperty("data_vencimento")
        private LocalDate dateVenc;

        @JsonFormat(pattern = DATETIME_JSON_PATTERN)
        @JsonProperty("data_pagamento")
        private LocalDate datePayment;

        @JsonProperty("valor")
        private Double amount;

        @JsonProperty("descricao")
        private String description;

        @JsonProperty("situacao")
        private AccountStatusEnum status;
    }
}
