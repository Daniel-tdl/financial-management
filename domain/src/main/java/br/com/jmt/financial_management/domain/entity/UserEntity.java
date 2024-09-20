package br.com.jmt.financial_management.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import jakarta.persistence.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@With
@Data
@Entity
@Table(name = "Usuarios")
public class UserEntity {

    @Id
    @Column
    private String username;

    @Column
    private String password;
}
