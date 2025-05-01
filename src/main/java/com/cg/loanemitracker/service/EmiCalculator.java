package com.cg.loanemitracker.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EmiCalculator {
    public BigDecimal calculateEmi(BigDecimal principal, BigDecimal annualRate, int tenureMonths) {
        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);
        BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(tenureMonths);

        return principal.multiply(monthlyRate)
                .multiply(factor)
                .divide(factor.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTotalInterest(BigDecimal principal, BigDecimal annualRate, int tenureMonths) {
        BigDecimal emi = calculateEmi(principal, annualRate, tenureMonths);
        return emi.multiply(BigDecimal.valueOf(tenureMonths)).subtract(principal);
    }

    public BigDecimal calculateTotalPayment(BigDecimal principal, BigDecimal annualRate, int tenureMonths) {
        return calculateEmi(principal, annualRate, tenureMonths)
                .multiply(BigDecimal.valueOf(tenureMonths));
    }
}
