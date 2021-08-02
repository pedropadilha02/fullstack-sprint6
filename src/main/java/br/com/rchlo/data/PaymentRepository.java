package br.com.rchlo.data;

import br.com.rchlo.domain.Card;
import br.com.rchlo.domain.Payment;
import br.com.rchlo.domain.PaymentStatus;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepository {

    public List<Payment> all() {

        List<Payment> allPayments = new ArrayList<>();

        String query = "select id, amount, card_holder_name, card_number, card_expiration, card_verification_code, status" +
                " from payment";

        ConnectionFactory.init();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                long id = resultSet.getLong("id");
                BigDecimal amount = resultSet.getBigDecimal("amount");

                String cardHolderName = resultSet.getString("card_holder_name");
                String cardNumber = resultSet.getString("card_number");
                YearMonth cardExpiration = YearMonth.parse(resultSet.getString("card_expiration"));
                String cardVerificationCode = resultSet.getString("card_verification_code");

                var card = new Card(cardHolderName, cardNumber, cardExpiration, cardVerificationCode);

                var status = PaymentStatus.valueOf(resultSet.getString("status"));

                var pagamento = new Payment(id, amount, card, status);

                allPayments.add(pagamento);

            }
        } catch (SQLException ex) {
            throw new IllegalStateException("Error retrieving payments", ex);
        }

        return allPayments;
    }

    public BigDecimal maximumAmountOfConfirmedPayment() {
        String query = "select max(p.amount) as max from payment p where p.status = ?";
        ConnectionFactory.init();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, PaymentStatus.CONFIRMED.name());

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return resultSet.getBigDecimal("max");
            }

        } catch (SQLException ex) {
            throw new IllegalStateException("Error retrieving maximum amount", ex);
        }

        return BigDecimal.ZERO;
    }

    public Map<PaymentStatus, Long> quantityByPaymentStatus() {
        Map<PaymentStatus, Long> map = new HashMap<>();

        String query = "select p.status as status, count(*) as quantity from payment p group by p.status";

        ConnectionFactory.init();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                PaymentStatus status = PaymentStatus.valueOf(resultSet.getString("status"));
                long quantity = resultSet.getLong("quantity");
                map.put(status, quantity);
            }

        } catch (SQLException ex) {
            throw new IllegalStateException("Error retrieving maximum amount", ex);
        }

        return map;
    }

}
