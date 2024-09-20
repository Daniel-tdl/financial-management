package br.com.jmt.financial_management.domain.entity;

import br.com.jmt.financial_management.domain.entity.base.BaseEntity;
import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@With
@Data
@Entity
@Table(name = "Conta")
public class AccountEntity extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_vencimento")
    private LocalDate dateVenc;

    @Column(name = "data_pagamento")
    private LocalDate datePayment;

    @Column(name = "valor")
    private Double amount;

    @Column(name = "descricao", length = 2000)
    private String description;

    @Column(name = "situacao", length = 100)
    @Enumerated(EnumType.STRING)
    private AccountStatusEnum status;
}
