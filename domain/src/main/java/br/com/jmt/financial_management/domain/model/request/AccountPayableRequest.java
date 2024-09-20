package br.com.jmt.financial_management.domain.model.request;

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
public class AccountPayableRequest {

    @JsonProperty("data_vencimento")
    @NotNull(message = "Informe a data de vencimento.")
    private LocalDate dateVenc;

    @JsonProperty("descricao")
    @NotEmpty(message = "Informe a descrição.")
    private String description;

    private Integer page = 0;
    private Integer size = 10;
}
