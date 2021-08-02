package br.com.rchlo.dto;

import br.com.rchlo.domain.PaymentStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PaymentStatistics {

    private final Map<PaymentStatus, Long> quantityByStatus = new HashMap<>();
    private final BigDecimal maximumAmountOfConfirmedPayment;

    public PaymentStatistics(BigDecimal maximumAmountOfConfirmedPayment) {
        this.maximumAmountOfConfirmedPayment = maximumAmountOfConfirmedPayment;
        Arrays.stream(PaymentStatus.values()).forEach(status -> quantityByStatus.put(status, 0L));
    }

    public BigDecimal getMaximumAmountOfConfirmedPayment() {
        return maximumAmountOfConfirmedPayment;
    }

    public Map<PaymentStatus, Long> getQuantityOfPaymentsByStatus() {
        return quantityByStatus;
    }

    public void addPaymentForStatus(PaymentStatus status) {
        Long quantity = quantityByStatus.get(status);
        quantityByStatus.put(status, quantity + 1);
    }

}
