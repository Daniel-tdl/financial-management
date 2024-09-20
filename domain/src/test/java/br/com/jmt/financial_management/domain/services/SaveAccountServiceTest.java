package br.com.jmt.financial_management.domain.services;

import br.com.jmt.financial_management.domain.entity.AccountEntity;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class SaveAccountServiceTest {

    @Mock
    private AccountPort accountPort;

    @InjectMocks
    private AccountMapper accountMapper = new AccountMapperImpl();

    @InjectMocks
    private SaveAccountService service;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(service, "accountMapper", accountMapper);
    }

    @Test
    void givenExecuteWithSuccess() {
        Mockito.when(accountPort.save(any())).thenReturn(getAccountMock());
        var response = service.execute(getRequestMock());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.getId());
    }

    private AccountRequest getRequestMock() {
        return AccountRequest.builder()
                .amount(32.2)
                .datePayment(LocalDate.now())
                .dateVenc(LocalDate.now())
                .build();
    }

    private AccountEntity getAccountMock() {
        AccountEntity entity = new AccountEntity();
        entity.setId(1L);
        entity.setDatePayment(LocalDate.now());
        return entity;
    }
}
