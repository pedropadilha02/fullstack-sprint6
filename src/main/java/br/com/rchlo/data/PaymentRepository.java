package br.com.rchlo.data;

import br.com.rchlo.domain.PaymentStatus;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {

    private final Connection connection;

    public PaymentRepository(Connection connection) {
        this.connection = connection;
    }

    public BigDecimal maximumAmountOfConfirmedPayment() {
        String query = "select max(p.amount) as max from payment p where p.status = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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
