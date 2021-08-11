package br.com.rchlo.main;


import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.filter.Criteria;
import br.com.rchlo.service.filter.Filter;

import java.math.BigDecimal;
import java.util.List;

public class ProductsByEffectivePriceRangeMain {

    public static void main(String[] args) {

        List<Product> allProducts = ProductRepository.all();

        BigDecimal minimumPrice = new BigDecimal("30.00");
        BigDecimal maximumPrice = new BigDecimal("50.00");

        var filter = new Filter();
        Criteria byEffectivePriceRange = product -> {
            BigDecimal effectivePrice = product.getEffectivePrice();
            return effectivePrice.compareTo(minimumPrice) >= 0 &&
                    effectivePrice.compareTo(maximumPrice) <= 0;
        };

        List<Product> filteredProducts = filter.execute(byEffectivePriceRange, allProducts);

        for (Product product : filteredProducts) {
            BigDecimal effectivePrice = product.getEffectivePrice();
            System.out.printf("%s - %s - R$ %.2f %n", product.getCode(), product.getName(), effectivePrice);
        }

    }

}
