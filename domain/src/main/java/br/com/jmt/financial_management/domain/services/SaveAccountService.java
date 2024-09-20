package br.com.jmt.financial_management.domain.services;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.exceptions.ErrorAccountException;
import br.com.jmt.financial_management.domain.mapper.AccountMapper;
import br.com.jmt.financial_management.domain.model.dto.AccountDTO;
import br.com.jmt.financial_management.domain.model.request.AccountRequest;
import br.com.jmt.financial_management.domain.ports.in.SaveAccountUseCase;
import br.com.jmt.financial_management.domain.ports.out.AccountPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SaveAccountService implements SaveAccountUseCase {

    private final AccountPort accountPort;
    private final AccountMapper accountMapper;

    @Override
    public AccountDTO execute(AccountRequest request) {
        try {
            AccountEntity entity = accountMapper.buildRequestToEntity(request);
            var entityDB = accountPort.save(entity);
            var response = accountMapper.buildEntityToDTO(entityDB);
            log.info("conta de id: {} criada com sucesso.", entityDB.getId());
            return response;
        } catch (Exception e) {
            log.error("Erro ao criar a conta de descrição. {}. Erro => {}", request.getDescription(), Objects.nonNull(e.getMessage()) ? e.getMessage() : null);
            throw new ErrorAccountException("Erro ao criar a conta!");
        }
    }
}