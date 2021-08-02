package br.com.rchlo.dto;

import br.com.rchlo.domain.PaymentStatus;

import java.math.BigDecimal;
import java.util.Map;

public class PaymentStatistics {

    private final BigDecimal maximumAmountOfConfirmedPayment;
    private final Map<PaymentStatus, Long> quantityByStatus;

    public PaymentStatistics(BigDecimal maximumAmountOfConfirmedPayment, Map<PaymentStatus, Long> quantityByStatus) {
        this.maximumAmountOfConfirmedPayment = maximumAmountOfConfirmedPayment;
        this.quantityByStatus = quantityByStatus;
    }

    public BigDecimal getMaximumAmountOfConfirmedPayment() {
        return maximumAmountOfConfirmedPayment;
    }

    public Map<PaymentStatus, Long> getQuantityOfPaymentsByStatus() {
        return quantityByStatus;
    }

}
