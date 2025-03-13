package com.backend.smartwalletapp.client.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.backend.smartwalletapp.client.requests.Contract.GetContractByCardHolderSoapRequest;
import com.backend.smartwalletapp.client.requests.Contract.GetContractBysearchSoapRequest;
import com.backend.smartwalletapp.client.requests.Contract.LockOrUnlockContractSoapRequest;
import com.backend.smartwalletapp.client.requests.Contract.UpdateLimitContractSoapRequest;
import com.backend.smartwalletapp.client.requests.Contract.CreateContractLevel2.CreateContractV4_REQV2;
import com.backend.smartwalletapp.client.requests.Contract.CreateContractV4.CreateContractV4_REQ;
import com.backend.smartwalletapp.client.responses.Contract.GetContractByCardHolderSoapResponse;
import com.backend.smartwalletapp.client.responses.Contract.GetContractBySearchSoapResponse;
import com.backend.smartwalletapp.client.responses.Contract.LockOrUnLockContractSoapResponse;
import com.backend.smartwalletapp.client.responses.Contract.UpdateLimitContractSoapResponse;
import com.backend.smartwalletapp.client.responses.Contract.create.CreateContractV4Response;
import com.backend.smartwalletapp.client.responses.Contract.create.CreateContractV4Result;
import com.backend.smartwalletapp.client.responses.Contract.create.V2CreateContractV4Response;
import com.backend.smartwalletapp.exception.AppException;
import com.backend.smartwalletapp.exception.ErrorCode;
import jakarta.xml.bind.JAXBElement;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractSoapService {
    private final WebServiceTemplate webServiceTemplate;

    @Value("${Way4Url.ApiKey}")
    private String soapUrl;

    @SuppressWarnings("unchecked")
    public GetContractBySearchSoapResponse getContractBySearch(String search, int page) {
        String fullString = soapUrl;
        GetContractBysearchSoapRequest request = new GetContractBysearchSoapRequest(search, page);
        try {
            JAXBElement<GetContractBySearchSoapResponse> response =
            (JAXBElement<GetContractBySearchSoapResponse>)
            webServiceTemplate.marshalSendAndReceive(fullString, request);
            return response.getValue();
        } catch (Exception e) {
            throw new AppException(ErrorCode.GET_CONTRACT_FAILE);
        }
    }

    @SuppressWarnings("unchecked")
    public GetContractByCardHolderSoapResponse getContractByCardHolder(String cardHolderId) {
        String fullString = soapUrl + "/cardHolder";
        GetContractByCardHolderSoapRequest request = new GetContractByCardHolderSoapRequest(cardHolderId);
        try {
            JAXBElement<GetContractByCardHolderSoapResponse> response =
            (JAXBElement<GetContractByCardHolderSoapResponse>)
            webServiceTemplate.marshalSendAndReceive(fullString, request);
            return response.getValue();
        } catch (Exception e) {
            throw new AppException(ErrorCode.GET_CONTRACT_FAILE);
        }
    }

    public CreateContractV4Result createContract(CreateContractV4_REQ request) {
        try {
            System.err.println("\n ------------request CreateContractV4_REQ :---------------------\n " + request);
            CreateContractV4Response response = (CreateContractV4Response) webServiceTemplate.marshalSendAndReceive(soapUrl, request);
            System.err.println("\n ------------response CreateContractV4Response :-----------------\n " + response);
            System.err.println("\n response CreateContractV4Response :\n " + response.getCreateContractV4Result());
            CreateContractV4Result result = response.getCreateContractV4Result();
            System.err.println("\n -----------result CreateContractV4Result :----------------------\n " + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.GET_CONTRACT_FAILE);
        }
    }

    public CreateContractV4Result createContractLevel2(CreateContractV4_REQV2 request) {
        try {
            System.err.println("\n ------------request CreateContractV4_REQ :---------------------\n " + request);
            V2CreateContractV4Response response = (V2CreateContractV4Response) webServiceTemplate.marshalSendAndReceive(soapUrl, request);
            System.err.println("\n ------------response CreateContractV4Response :-----------------\n " + response);
            System.err.println("\n response CreateContractV4Response :\n " + response.getCreateContractV4Result());
            CreateContractV4Result result = response.getCreateContractV4Result();
            System.err.println("\n -----------result CreateContractV4Result :----------------------\n " + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.GET_CONTRACT_FAILE);
        }
    }

    @SuppressWarnings("unchecked")
    public UpdateLimitContractSoapResponse UpdateLimitContractByCardHolder(String contractId, BigDecimal limit) {
        String fullString = soapUrl + "/cardHolder";
        UpdateLimitContractSoapRequest request = new UpdateLimitContractSoapRequest(contractId, limit);
        try {
            JAXBElement<UpdateLimitContractSoapResponse> response =
            (JAXBElement<UpdateLimitContractSoapResponse>)
            webServiceTemplate.marshalSendAndReceive(fullString, request);
            return response.getValue();
        } catch (Exception e) {
            throw new AppException(ErrorCode.UPDATE_CONTRACT_FAILE);
        }
    }

    @SuppressWarnings("unchecked")
    public LockOrUnLockContractSoapResponse LockOrUnLockContractSoap(String contractId, boolean newstatus) {
        String fullString = soapUrl + "/cardHolder";
        LockOrUnlockContractSoapRequest request = new LockOrUnlockContractSoapRequest(contractId, newstatus);
        try {
            JAXBElement<LockOrUnLockContractSoapResponse> response =
            (JAXBElement<LockOrUnLockContractSoapResponse>)
            webServiceTemplate.marshalSendAndReceive(fullString, request);
            return response.getValue();
        } catch (Exception e) {
            throw new AppException(ErrorCode.LOCK_UNLOCK_FAILE);
        }
    }
}
