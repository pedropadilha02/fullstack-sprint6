package br.com.rchlo.mother;

import br.com.rchlo.domain.Card;
import br.com.rchlo.domain.Payment;

import java.math.BigDecimal;
import java.time.Month;
import java.time.YearMonth;

import static br.com.rchlo.domain.PaymentStatus.*;

public class PaymentMother {

    public static Payment aCreatedPayment() {
        return new Payment(1L, new BigDecimal("51.8"), aCard(), CREATED);
    }

    public static Payment anotherCreatedPayment() {
        return new Payment(2L, new BigDecimal("3000.0"), aCard(), CREATED);
    }

    public static Payment aConfirmedPayment() {
        return new Payment(3L, new BigDecimal("200.0"), aCard(), CONFIRMED);
    }

    public static Payment aCanceledPayment() {
        return new Payment(4L, new BigDecimal("400.0"), aCard(), CANCELED);
    }

    public static Card aCard() {
        return new Card("ANDERSON DA SILVA", "1111 2222 3333 4444",
                YearMonth.of(2022, Month.JULY), "123");
    }

}
