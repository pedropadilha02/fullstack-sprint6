package br.com.rchlo.service;

import br.com.rchlo.data.PaymentRepository;
import br.com.rchlo.domain.Card;
import br.com.rchlo.domain.Payment;
import br.com.rchlo.domain.PaymentStatus;
import br.com.rchlo.dto.PaymentStatistics;
import br.com.rchlo.service.PaymentStatisticsCalculator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class PaymentStatisticsCalculatorTest {

    @Mock
    private PaymentRepository paymentRepository;


    private PaymentStatisticsCalculator paymentStatisticsCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentStatisticsCalculator = new PaymentStatisticsCalculator(paymentRepository);
        List<Payment> fakeList = paymentFake();
        Mockito.when(paymentRepository.all())
                .thenReturn(fakeList);
    }

    @Test
    void shouldCalculateMaximumAmountOfConfirmedPayment() {

        PaymentStatistics paymentStatistics = paymentStatisticsCalculator.calculate();
        BigDecimal maximumAmountOfConfirmedPayment = paymentStatistics.getMaximumAmountOfConfirmedPayment();
        Assertions.assertThat(maximumAmountOfConfirmedPayment).isEqualTo(new BigDecimal("600.00"));

    }

    @Test
    void deveConsiderarQuantidadeDePagamentoPorStatus() {

        PaymentStatistics paymentStatistics = paymentStatisticsCalculator.calculate();
        Map<PaymentStatus, Long> quantityOfPaymentsByStatus = paymentStatistics.getQuantityOfPaymentsByStatus();
        Assertions.assertThat(quantityOfPaymentsByStatus)
                .containsEntry(PaymentStatus.CREATED, 2L)
                .containsEntry(PaymentStatus.CONFIRMED, 1L)
                .containsEntry(PaymentStatus.CANCELED, 1L);

    }



    public List<Payment> paymentFake(){

        List<Payment> listPaymentFake = new ArrayList();

        Card fakeCard = new Card("holderFake", "111", YearMonth.now(), "456789");

        Payment paymentFake1 = new Payment(new BigDecimal("200.00"), fakeCard, PaymentStatus.CREATED);
        Payment paymentFake2 = new Payment(new BigDecimal("400.00"), fakeCard, PaymentStatus.CREATED);
        Payment paymentFake3 = new Payment(new BigDecimal("600.00"), fakeCard, PaymentStatus.CONFIRMED);
        Payment paymentFake4 = new Payment(new BigDecimal("800.00"), fakeCard, PaymentStatus.CANCELED);

        listPaymentFake.add(paymentFake1);
        listPaymentFake.add(paymentFake2);
        listPaymentFake.add(paymentFake3);
        listPaymentFake.add(paymentFake4);


        return listPaymentFake;
    }
}