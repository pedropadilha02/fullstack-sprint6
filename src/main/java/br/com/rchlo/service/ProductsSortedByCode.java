package br.com.rchlo.service;

import br.com.rchlo.domain.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductsSortedByCode {

    public List<Product> sortByCode(List<Product> originalProductList) {
        validateParameters(originalProductList);

        List<Product> products = new ArrayList<>(originalProductList);
        products.sort(Comparator.comparing(Product::getCode));
        return products;
    }

    private void validateParameters(List<Product> products) {
        if (products == null) throw new IllegalArgumentException("product list should not be null");
    }

}
