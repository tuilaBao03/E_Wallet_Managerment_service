package com.backend.smartwalletapp.controller;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.smartwalletapp.dto.request.Contract.LockUnLockContracRequest;
import com.backend.smartwalletapp.dto.request.Contract.UpdateLimitContractRequest;
import com.backend.smartwalletapp.dto.response.ApiResponse;
import com.backend.smartwalletapp.dto.response.Contract.ContractResponse;
import com.backend.smartwalletapp.model.Card;
import com.backend.smartwalletapp.model.Contract;
import com.backend.smartwalletapp.model.Transaction;
import com.backend.smartwalletapp.service.CardSevice;
import com.backend.smartwalletapp.service.ContractService;
import com.backend.smartwalletapp.service.TransactionService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/smartwalletapp/contract")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ContractController {
    ContractService contractService;
    TransactionService transactionService;
    CardSevice cardSevice;
    
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole(Roles.USER.name()) or hasRole(Roles.ADMIN.name())")
    public ApiResponse<Contract> LockUnLockContract(
        @PathVariable String id,
        @RequestBody @Valid LockUnLockContracRequest request){
        return ApiResponse.<Contract>builder()
        .result(contractService.LockUnlockContract(id, request))
        .code(200)
        .build();
    }

    @PatchMapping("/{id}/Limit")
    @PreAuthorize("hasRole(Roles.USER.name()) or hasRole(Roles.ADMIN.name())")
    ApiResponse<Contract> UpdateLimitContract(
        @RequestBody @Valid UpdateLimitContractRequest request,
        @PathVariable String id
    ){
        return ApiResponse.<Contract>builder()
        .result(contractService.updateLimit(id, request.getNewLimit()))
            .code(200)
            .message("cardholder success").build();
        
    }


    @PatchMapping("/{id}/cards")
    @PreAuthorize("hasRole(Roles.USER.name()) or hasRole(Roles.ADMIN.name())")
    public ApiResponse<List<Card>> GetCardByContract(
        @PathVariable String id){
        return ApiResponse.<List<Card>>builder()
        .result(cardSevice.getCardbyContract(id))
        .code(200)
        .build();
    }

    @PatchMapping("/{id}/transactions")
    @PreAuthorize("hasRole(Roles.USER.name()) or hasRole(Roles.ADMIN.name())")
    public ApiResponse<List<Transaction>> GetTransByContract(
        @PathVariable String id){
        return ApiResponse.<List<Transaction>>builder()
        .result(transactionService.getTransactionByContract(id))
        .code(200)
        .build();
    }

    @PatchMapping("/{search}/{page}")
    @PreAuthorize("hasRole(Roles.USER.name()) or hasRole(Roles.ADMIN.name())")
    public ApiResponse<ContractResponse> GetContractBySearchAndPage(
        @PathVariable String search,
        @PathVariable int page){
        return ApiResponse.<ContractResponse>builder()
        .result(contractService.giveContractBySearch(search, page))
        .code(200)
        .build();
    }

}
