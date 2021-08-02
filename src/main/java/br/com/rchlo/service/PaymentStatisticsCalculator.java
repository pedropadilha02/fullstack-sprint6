package br.com.rchlo.service;

import br.com.rchlo.data.PaymentRepository;
import br.com.rchlo.domain.PaymentStatus;
import br.com.rchlo.dto.PaymentStatistics;

import java.math.BigDecimal;
import java.util.Map;

public class PaymentStatisticsCalculator {

    private final PaymentRepository paymentRepository;

    public PaymentStatisticsCalculator(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentStatistics calculate() {
        BigDecimal maximumConfirmedPayment = paymentRepository.maximumAmountOfConfirmedPayment();
        Map<PaymentStatus, Long> quantityByPaymentStatus = paymentRepository.quantityByPaymentStatus();
        return new PaymentStatistics(maximumConfirmedPayment, quantityByPaymentStatus);
    }
}
