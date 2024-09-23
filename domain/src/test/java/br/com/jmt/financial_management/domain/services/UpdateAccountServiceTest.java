package br.com.jmt.financial_management.domain.services;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import br.com.jmt.financial_management.domain.exceptions.AccountNotFoundException;
import br.com.jmt.financial_management.domain.exceptions.ErrorAccountException;
import br.com.jmt.financial_management.domain.mapper.AccountMapper;
import br.com.jmt.financial_management.domain.mapper.AccountMapperImpl;
import br.com.jmt.financial_management.domain.model.request.AccountRequest;
import br.com.jmt.financial_management.domain.ports.out.AccountPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UpdateAccountServiceTest {

    @Mock
    private AccountPort accountPort;

    @InjectMocks
    private AccountMapper accountMapper = new AccountMapperImpl();

    @InjectMocks
    private UpdateAccountService service;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "accountMapper", accountMapper);
    }

    @Test
    void givenExecuteWithSuccess() {
        Mockito.when(accountPort.findById(any())).thenReturn(Optional.of(getAccountMock()));
        Mockito.when(accountPort.save(any())).thenReturn(getAccountMock());
        var response = service.execute(1L, getAccountRequestMock());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.getId());
    }

    @Test
    void givenUpdateStatusWithSuccess() {
        Mockito.when(accountPort.findById(any())).thenReturn(Optional.of(getAccountMock()));
        Mockito.when(accountPort.save(any())).thenReturn(getAccountMock());
        var response = service.updateStatus(1L, AccountStatusEnum.PAID);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.getId());
    }

    @Test
    void givenUpdateStatusWithError() {
        Mockito.when(accountPort.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountNotFoundException.class, () -> service.updateStatus(1L, AccountStatusEnum.PENDING));
    }

    @Test
    void givenExecuteWithError() {
        Mockito.when(accountPort.findById(any())).thenReturn(Optional.empty());
        Assertions.assertThrows(AccountNotFoundException.class, () -> service.execute(1L, getAccountRequestMock()));
    }

    @Test
    void givenExecuteWithGenericError() {
        Mockito.when(accountPort.findById(any())).thenReturn(Optional.of(getAccountMock()));
        Mockito.when(accountPort.save(any())).thenReturn(null);
        Assertions.assertThrows(ErrorAccountException.class, () -> service.execute(1L, getAccountRequestMock()));
    }

    @Test
    void givenUpdateStatusWithGenericError() {
        Mockito.when(accountPort.findById(any())).thenReturn(Optional.of(getAccountMock()));
        Mockito.when(accountPort.save(any())).thenReturn(null);
        Assertions.assertThrows(ErrorAccountException.class, () -> service.updateStatus(1L, AccountStatusEnum.PAID));
    }

    private AccountRequest getAccountRequestMock() {
        return AccountRequest.builder()
                .dateVenc(LocalDate.now())
                .status(AccountStatusEnum.PAID)
                .description("teste")
                .build();
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
