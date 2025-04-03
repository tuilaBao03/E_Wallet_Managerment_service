package com.backend.smartwalletapp.client.requests.Card;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "ActivateCard",namespace = "http://www.openwaygroup.com/wsint")
public class ActivateCard {

    @XmlElement(name = "ContractSearchMethod", namespace = "http://www.openwaygroup.com/wsint")
    String contractSearchMethod;

    @XmlElement(name = "ContractIdentifier", namespace = "http://www.openwaygroup.com/wsint")
    String contractIdentifier;

    @XmlElement(name = "Reason", namespace = "http://www.openwaygroup.com/wsint")
    String reason;

}
