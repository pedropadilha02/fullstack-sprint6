package br.com.rchlo.service;

import br.com.rchlo.domain.Color;
import br.com.rchlo.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsByColor {

    public List<Product> filter(Color color, List<Product> products) {
        validateParameters(color, products);

        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (color.equals(product.getColor())) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    private void validateParameters(Color color, List<Product> products) {
        if (color == null) throw new IllegalArgumentException("code should not be null");
        if (products == null) throw new IllegalArgumentException("product list should not be null");
    }


}
