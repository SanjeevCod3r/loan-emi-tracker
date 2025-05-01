package com.cg.loanemitracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmiRequest {
    private BigDecimal principal;
    private BigDecimal annualInterestRate;
    private Integer tenureMonths;
}