package com.backend.smartwalletapp.client.responses.Card;

import javax.smartcardio.Card;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "LockOrUnLockCardSoapResponse")
@Data
public class LockOrUnLockCardSoapResponse {
    @XmlElement(name = "card")
    private Card card;
}
