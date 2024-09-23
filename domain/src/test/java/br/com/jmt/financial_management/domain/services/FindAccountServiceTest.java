package br.com.jmt.financial_management.domain.services;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import br.com.jmt.financial_management.domain.exceptions.AccountNotFoundException;
import br.com.jmt.financial_management.domain.exceptions.DataInvalidException;
import br.com.jmt.financial_management.domain.mapper.AccountMapper;
import br.com.jmt.financial_management.domain.mapper.AccountMapperImpl;
import br.com.jmt.financial_management.domain.model.request.AccountPayableRequest;
import br.com.jmt.financial_management.domain.model.request.AccountTotalAmountRequest;
import br.com.jmt.financial_management.domain.ports.out.AccountPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.util.ReflectionTestUtils;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class FindAccountServiceTest {

    @Mock
    private AccountPort accountPort;

    @InjectMocks
    private AccountMapper accountMapper = new AccountMapperImpl();

    @InjectMocks
    private FindAccountService service;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "accountMapper", accountMapper);
    }

    @Test
    void givenFindByIdWithSuccess() {
        Mockito.when(accountPort.findById(any())).thenReturn(Optional.of(getAccountMock()));
        var response = service.findById(any());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.getId());
    }

    @Test
    void givenFindByIdWithError() {
        Mockito.when(accountPort.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountNotFoundException.class, () -> service.findById(any()));
    }

    @Test
    void givenFindPayableWithSuccess() {

        Mockito.when(accountPort.findAll(any(), any())).thenReturn(getPageAccountMock());
        var response = service.findPayable(getPayableRequestMock());
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.getContents().isEmpty());
        Assertions.assertEquals(2, response.getContents().size());
    }

    @Test
    void givenGetTotalAmountPaidPerPeriodWithSuccess() {

        var request = AccountTotalAmountRequest.builder()
                .page(0)
                .size(10)
                .dateStart(LocalDate.now().minusDays(1))
                .dateEnd(LocalDate.now().plusDays(1))
                .build();

        Mockito.when(accountPort.findAll(any(), any())).thenReturn(getPageAccountMock());
        var response = service.getTotalAmountPaidPerPeriod(request);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(300.4D, response.getTotalAmount());
    }

    @Test
    void givenGetTotalAmountPaidPerPeriodWithError() {

        var request = AccountTotalAmountRequest.builder()
                .page(0)
                .size(10)
                .dateStart(LocalDate.now().plusDays(1))
                .dateEnd(LocalDate.now())
                .build();

        Assertions.assertThrows(DataInvalidException.class, () -> service.getTotalAmountPaidPerPeriod(request));
    }

    private AccountPayableRequest getPayableRequestMock() {
        return AccountPayableRequest.builder()
                .page(0)
                .size(10)
                .build();
    }

    private Page<AccountEntity> getPageAccountMock() {
        List<AccountEntity> entities = new ArrayList<>();
        var entity = getAccountMock();
        entities.add(entity);

        var entityTwo = getAccountMock();
        entityTwo.setAmount(100.2);
        entityTwo.setId(2L);
        entities.add(entityTwo);
        return new PageImpl(entities);
    }

    private AccountEntity getAccountMock() {
        AccountEntity entity = new AccountEntity();
        entity.setId(1L);
        entity.setDatePayment(LocalDate.now());
        entity.setStatus(AccountStatusEnum.PAID);
        entity.setAmount(200.2);
        return entity;
    }
}
