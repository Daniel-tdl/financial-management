package br.com.jmt.financial_management.domain.ports.in;

import br.com.jmt.financial_management.domain.model.dto.AccountDTO;
import br.com.jmt.financial_management.domain.model.dto.AccountPayableDTO;
import br.com.jmt.financial_management.domain.model.dto.AccountTotalAmountDTO;
import br.com.jmt.financial_management.domain.model.request.AccountPayableRequest;
import br.com.jmt.financial_management.domain.model.request.AccountRequest;
import br.com.jmt.financial_management.domain.model.request.AccountTotalAmountRequest;

public interface FindAccountUseCase {
    AccountDTO findById(Long id);
    AccountPayableDTO findPayable(AccountPayableRequest request);
    AccountTotalAmountDTO getTotalAmountPaidPerPeriod(AccountTotalAmountRequest request);
}
