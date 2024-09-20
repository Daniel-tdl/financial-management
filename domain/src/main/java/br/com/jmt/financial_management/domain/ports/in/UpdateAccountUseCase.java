package br.com.jmt.financial_management.domain.ports.in;

import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import br.com.jmt.financial_management.domain.model.dto.AccountDTO;
import br.com.jmt.financial_management.domain.model.request.AccountRequest;

public interface UpdateAccountUseCase {
    AccountDTO execute(Long id, AccountRequest request);
    AccountDTO updateStatus(Long id, AccountStatusEnum status);
}
