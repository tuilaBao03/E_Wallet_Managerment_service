package com.backend.smartwalletapp.client.responses.Contract.create;
import jakarta.xml.bind.annotation.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@XmlRootElement(name = "CreateIssuingContractWithLiabilityV2Response", namespace = "http://www.openwaygroup.com/wsint")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class V2CreateContractV4Response {
    @XmlElement(name = "CreateIssuingContractWithLiabilityV2Result", namespace = "http://www.openwaygroup.com/wsint")
    CreateContractV4Result createContractV4Result;
}


