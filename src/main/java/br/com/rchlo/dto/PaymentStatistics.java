package br.com.rchlo.dto;

import br.com.rchlo.data.PaymentRepository;
import br.com.rchlo.domain.PaymentStatus;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class PaymentStatistics {


    private PaymentRepository paymentRepository;

    public PaymentStatistics(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public BigDecimal getMaximumAmountOfConfirmedPayment() {
        return this.paymentRepository.getMaximumPayment();
    }

    public Map<PaymentStatus, Long> getQuantityOfPaymentsByStatus() {
        return this.paymentRepository.getListByStatus();
    }

}