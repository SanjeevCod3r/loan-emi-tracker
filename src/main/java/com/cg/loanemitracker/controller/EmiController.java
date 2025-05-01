package com.cg.loanemitracker.controller;

import com.cg.loanemitracker.dto.EmiRequest;
import com.cg.loanemitracker.dto.EmiResponse;
import com.cg.loanemitracker.service.EmiCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/loans/calculate-emi")
@RequiredArgsConstructor
public class EmiController {
    private final EmiCalculator emiCalculator;

    @PostMapping
    public ResponseEntity<EmiResponse> calculateEmi(@RequestBody EmiRequest request) {
        BigDecimal emi = emiCalculator.calculateEmi(
                request.getPrincipal(),
                request.getAnnualInterestRate(),
                request.getTenureMonths()
        );

        BigDecimal totalInterest = emiCalculator.calculateTotalInterest(
                request.getPrincipal(),
                request.getAnnualInterestRate(),
                request.getTenureMonths()
        );

        BigDecimal totalPayment = emiCalculator.calculateTotalPayment(
                request.getPrincipal(),
                request.getAnnualInterestRate(),
                request.getTenureMonths()
        );

        EmiResponse response = EmiResponse.builder()
                .emi(emi)
                .totalInterest(totalInterest)
                .totalPayment(totalPayment)
                .build();

        return ResponseEntity.ok(response);
    }
}
