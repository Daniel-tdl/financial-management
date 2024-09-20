package br.com.jmt.financial_management.domain.ports.in;

import br.com.jmt.financial_management.domain.model.dto.AccountDTO;
import br.com.jmt.financial_management.domain.model.request.AccountRequest;

public interface SaveAccountUseCase {
    AccountDTO execute(AccountRequest request);
}
