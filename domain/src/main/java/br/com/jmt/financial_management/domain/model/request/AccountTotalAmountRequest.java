package br.com.jmt.financial_management.domain.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AccountTotalAmountRequest {

    @JsonProperty("data_inicial")
    @NotNull(message = "Informe a data de inicio.")
    private LocalDate dateStart;

    @JsonProperty("data_final")
    @NotNull(message = "Informe a data de final.")
    private LocalDate dateEnd;

    private Integer page = 0;
    private Integer size = 10;
}
