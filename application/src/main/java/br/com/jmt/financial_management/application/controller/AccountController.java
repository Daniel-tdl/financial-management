package br.com.jmt.financial_management.application.controller;

import br.com.jmt.financial_management.domain.enums.AccountStatusEnum;
import br.com.jmt.financial_management.domain.model.dto.AccountDTO;
import br.com.jmt.financial_management.domain.model.dto.AccountPayableDTO;
import br.com.jmt.financial_management.domain.model.dto.AccountTotalAmountDTO;
import br.com.jmt.financial_management.domain.model.request.AccountPayableRequest;
import br.com.jmt.financial_management.domain.model.request.AccountRequest;
import br.com.jmt.financial_management.domain.model.request.AccountTotalAmountRequest;
import br.com.jmt.financial_management.domain.ports.in.FindAccountUseCase;
import br.com.jmt.financial_management.domain.ports.in.SaveAccountUseCase;
import br.com.jmt.financial_management.domain.ports.in.UpdateAccountUseCase;
import br.com.jmt.financial_management.domain.ports.in.UploadAccountUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

  private SaveAccountUseCase saveAccountUseCase;
  private FindAccountUseCase findAccountUseCase;
  private UpdateAccountUseCase updateAccountUseCase;
  private UploadAccountUseCase uploadAccountUseCase;

  @PostMapping
  public ResponseEntity<AccountDTO> save(@Valid @RequestBody AccountRequest request) {
    return ResponseEntity.ok(saveAccountUseCase.execute(request));
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<AccountDTO> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(findAccountUseCase.findById(id));
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<AccountDTO> update(@PathVariable("id") Long id, @Valid @RequestBody AccountRequest request) {
    return ResponseEntity.ok(updateAccountUseCase.execute(id, request));
  }

  @PutMapping(value = "/situacao/{situacao}/id/{id}")
  public ResponseEntity<AccountDTO> updateStatus(@PathVariable("id") Long id, @PathVariable("situacao") AccountStatusEnum status) {
    return ResponseEntity.ok(updateAccountUseCase.updateStatus(id, status));
  }

  @GetMapping(value = "/list-accounts-payable")
  public ResponseEntity<AccountPayableDTO> listAccountsPayable(@Valid @RequestBody AccountPayableRequest request) {
    return ResponseEntity.ok(findAccountUseCase.findPayable(request));
  }

  @GetMapping(value = "/get-total-amount-paid-period")
  public ResponseEntity<AccountTotalAmountDTO> getTotalAmountPaidPerPeriod(@Valid @RequestBody AccountTotalAmountRequest request) {
    return ResponseEntity.ok(findAccountUseCase.getTotalAmountPaidPerPeriod(request));
  }

  @PostMapping(value = "/upload")
  public ResponseEntity uploadAccount(@RequestPart("file") MultipartFile file) {
    uploadAccountUseCase.execute(file);
    return ResponseEntity.ok().build();
  }
}
