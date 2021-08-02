package br.com.rchlo.service;

import br.com.rchlo.data.PaymentRepository;
import br.com.rchlo.domain.Payment;
import br.com.rchlo.domain.PaymentStatus;
import br.com.rchlo.dto.PaymentStatistics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static br.com.rchlo.mother.PaymentMother.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentStatisticsCalculatorTest {

    private PaymentRepository paymentRepository;
    private PaymentStatisticsCalculator paymentStatisticsCalculator;
    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        payments = List.of(aCreatedPayment(), anotherCreatedPayment(), aConfirmedPayment(), aCanceledPayment());
        paymentStatisticsCalculator = new PaymentStatisticsCalculator(paymentRepository);
    }

    @Test
    void shouldCalculateMaximumAmountOfConfirmedPayment() {
        when(paymentRepository.all()).thenReturn(payments);

        PaymentStatistics paymentStatistics = paymentStatisticsCalculator.calculate();

        BigDecimal maximumAmountOfConfirmedPayment = paymentStatistics.getMaximumAmountOfConfirmedPayment();
        Assertions.assertThat(maximumAmountOfConfirmedPayment)
                .isEqualTo(new BigDecimal("200.0"));
    }

    @Test
    void shouldCalculateQuantityByPaymentStatus() {
        when(paymentRepository.all()).thenReturn(payments);

        PaymentStatistics paymentStatistics = paymentStatisticsCalculator.calculate();

        Map<PaymentStatus, Long> quantityOfPaymentsByStatus = paymentStatistics.getQuantityOfPaymentsByStatus();
        Assertions.assertThat(quantityOfPaymentsByStatus)
                .containsEntry(PaymentStatus.CREATED, 2L)
                .containsEntry(PaymentStatus.CONFIRMED, 1L)
                .containsEntry(PaymentStatus.CANCELED, 1L);
    }

    @Test
    void shouldContainAllZerosQuantityWhenThereAreNoPayment() {
        when(paymentRepository.all()).thenReturn(List.of());

        PaymentStatistics paymentStatistics = paymentStatisticsCalculator.calculate();

        Map<PaymentStatus, Long> quantityOfPaymentsByStatus = paymentStatistics.getQuantityOfPaymentsByStatus();
        Assertions.assertThat(quantityOfPaymentsByStatus)
                .containsEntry(PaymentStatus.CREATED, 0L)
                .containsEntry(PaymentStatus.CONFIRMED, 0L)
                .containsEntry(PaymentStatus.CANCELED, 0L);
    }

}