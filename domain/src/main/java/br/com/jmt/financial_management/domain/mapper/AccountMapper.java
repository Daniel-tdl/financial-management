package br.com.jmt.financial_management.domain.mapper;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.model.dto.AccountDTO;
import br.com.jmt.financial_management.domain.model.dto.AccountPayableDTO;
import br.com.jmt.financial_management.domain.model.request.AccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    AccountEntity buildRequestToEntity(AccountRequest request);

    AccountDTO buildEntityToDTO(AccountEntity entityDB);

    default AccountEntity buildUpdateRequestToEntity(Long id, AccountRequest request) {
        var entity = buildRequestToEntity(request);
        entity.setId(id);
        return entity;
    }

    List<AccountPayableDTO.Content> buildEntitiesToAccountPayableDTO(List<AccountEntity> content);
}
