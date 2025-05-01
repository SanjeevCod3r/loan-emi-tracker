package com.cg.loanemitracker.dto;


import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmiResponse {
    private BigDecimal emi;
    private BigDecimal totalInterest;
    private BigDecimal totalPayment;
}
