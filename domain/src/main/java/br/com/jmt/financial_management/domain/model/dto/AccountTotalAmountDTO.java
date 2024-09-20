package br.com.jmt.financial_management.domain.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountTotalAmountDTO {

    @JsonProperty("valor_total_pago ")
    private Double totalAmount;
    private Pagination pagination;

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
}
