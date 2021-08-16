package br.com.rchlo.service;

import br.com.rchlo.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsByCode {

    public List<Product> filter(Long code, List<Product> products) {
        validateParameters(code, products);

        return products.stream().filter((Product filteredProduct) ->
                code.equals(filteredProduct.getCode())).toList();
    }

    private void validateParameters(Long code, List<Product> products) {
        if (code == null) throw new IllegalArgumentException("code should not be null");
        if (products == null) throw new IllegalArgumentException("product list should not be null");
    }

}
