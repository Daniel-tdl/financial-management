package br.com.jmt.financial_management.domain.services;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.exceptions.AccountNotFoundException;
import br.com.jmt.financial_management.domain.exceptions.DataInvalidException;
import br.com.jmt.financial_management.domain.exceptions.ErrorAccountException;
import br.com.jmt.financial_management.domain.mapper.AccountMapper;
import br.com.jmt.financial_management.domain.model.dto.AccountDTO;
import br.com.jmt.financial_management.domain.model.dto.AccountPayableDTO;
import br.com.jmt.financial_management.domain.model.dto.AccountTotalAmountDTO;
import br.com.jmt.financial_management.domain.model.request.AccountPayableRequest;
import br.com.jmt.financial_management.domain.model.request.AccountTotalAmountRequest;
import br.com.jmt.financial_management.domain.ports.in.FindAccountUseCase;
import br.com.jmt.financial_management.domain.ports.out.AccountPort;
import br.com.jmt.financial_management.domain.specification.AccountSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FindAccountService implements FindAccountUseCase {

    private final AccountPort accountPort;
    private final AccountMapper accountMapper;

    @Override
    public AccountDTO findById(Long id) {

        var entity = accountPort.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Id " + id + " não encontrado!"));
        return accountMapper.buildEntityToDTO(entity);
    }

    @Override
    public AccountPayableDTO findPayable(AccountPayableRequest request) {

        var spec = AccountSpecifications.accountPayableSpecification(request);
        PageRequest page = PageRequest.of(request.getPage(), request.getSize());
        var entities = accountPort.findAll(spec, page);
        return buildAccountPayableDTO(entities);
    }

    @Override
    public AccountTotalAmountDTO getTotalAmountPaidPerPeriod(AccountTotalAmountRequest request) {

        checkDataPeriod(request);
        var spec = AccountSpecifications.accountTotalAmountSpecification(request);
        PageRequest page = PageRequest.of(request.getPage(), request.getSize());
        var entities = accountPort.findAll(spec, page);
        return buildAccountTotalAmountDTO(entities);
    }

    private void checkDataPeriod(AccountTotalAmountRequest request) {
        if (request.getDateStart().isAfter(request.getDateEnd()))
            throw new DataInvalidException("Data inicial não pode esta maior que o data final do periodo!");
    }

    private AccountTotalAmountDTO buildAccountTotalAmountDTO(Page<AccountEntity> entities) {
        var page = entities.getPageable().isPaged() ? entities.getPageable().getPageNumber() : 0;
        var totalAmount = entities.stream().mapToDouble(AccountEntity::getAmount).sum();
        return AccountTotalAmountDTO.builder()
                .totalAmount(totalAmount)
                .pagination(AccountTotalAmountDTO.Pagination.builder()
                        .page(page)
                        .totalPages(entities.getTotalPages())
                        .sizeItems(entities.getTotalElements())
                        .build()
                )
                .build();
    }

    private AccountPayableDTO buildAccountPayableDTO(Page<AccountEntity> entities) {
        var page = entities.getPageable().isPaged() ? entities.getPageable().getPageNumber() : 0;
        return AccountPayableDTO.builder()
                .pagination(
                        AccountPayableDTO.Pagination.builder()
                                .page(page)
                                .totalPages(entities.getTotalPages())
                                .sizeItems(entities.getTotalElements())
                                .build()
                )
                .contents(accountMapper.buildEntitiesToAccountPayableDTO(entities.getContent()))
                .build();
    }
}