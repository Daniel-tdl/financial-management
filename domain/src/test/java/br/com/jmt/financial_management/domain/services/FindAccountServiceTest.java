package br.com.jmt.financial_management.domain.services;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
import br.com.jmt.financial_management.domain.exceptions.AccountNotFoundException;
import br.com.jmt.financial_management.domain.mapper.AccountMapper;
import br.com.jmt.financial_management.domain.mapper.AccountMapperImpl;
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

    private AccountEntity getAccountMock() {
        AccountEntity entity = new AccountEntity();
        entity.setId(1L);
        entity.setDatePayment(LocalDate.now());
        return entity;
    }
}
