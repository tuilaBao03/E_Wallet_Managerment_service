package com.backend.smartwalletapp.dto.response.Contract.GetContract;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContractListResponse {
    int page;
    int pageTotal;
    List<ContractBySearchAndPage> contracts;
}
