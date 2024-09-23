package br.com.jmt.financial_management.domain.services;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import br.com.jmt.financial_management.domain.exceptions.AccountNotFoundException;
import br.com.jmt.financial_management.domain.exceptions.ErrorAccountException;
import br.com.jmt.financial_management.domain.mapper.AccountMapper;
import br.com.jmt.financial_management.domain.model.dto.AccountDTO;
import br.com.jmt.financial_management.domain.model.request.AccountRequest;
import br.com.jmt.financial_management.domain.ports.in.UpdateAccountUseCase;
import br.com.jmt.financial_management.domain.ports.out.AccountPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UpdateAccountService implements UpdateAccountUseCase {

    private final AccountPort accountPort;
    private final AccountMapper accountMapper;

    @Override
    public AccountDTO execute(Long id, AccountRequest request) {

        getAccountById(id);

        try {
            var entity = accountMapper.buildUpdateRequestToEntity(id, request);
            var entityDB = accountPort.save(entity);
            var response = accountMapper.buildEntityToDTO(entityDB);
            log.info("conta de id: {} atualizada com sucesso.", entityDB.getId());
            return response;
        } catch (Exception e) {
            massageLog(id, e);
            throw new ErrorAccountException("Erro ao atualizar a conta " + id + " .");
        }
    }

    private AccountEntity getAccountById(Long id) {
        return accountPort.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Id " + id + " não encontrado!"));
    }

    @Override
    public AccountDTO updateStatus(Long id, AccountStatusEnum status) {
        var entity = getAccountById(id);

        try {
            entity.setStatus(status);
            var entityDB = accountPort.save(entity);
            var response = accountMapper.buildEntityToDTO(entityDB);
            log.info("Status da conta de id: {} atualizada com sucesso.", response.getId());
            return response;
        } catch (Exception e) {
            massageLog(id, e);
            throw new ErrorAccountException("Erro ao atualizar a conta " + id + " .");
        }
    }

    private static void massageLog(Long id, Exception e) {
        log.error("Erro ao atualizar a conta id: {}. Erro => {}", id, Objects.nonNull(e.getMessage()) ? e.getMessage() : null);
    }
}