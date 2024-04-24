package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String cardNumber;
    private String cardholderName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;
    private String securityCode;
    private String bankName;
    private String cardType;
    private String accountNumber;
    private String issuingCountry;
    private boolean chipEnabled;
}