package br.com.rchlo.service;

import br.com.rchlo.domain.Color;
import br.com.rchlo.domain.Product;

import java.util.List;

public class ProductsByColor {

    public List<Product> filter(Color color, List<Product> products) {

        validateParameters(color, products);

        var filtro = products.stream().filter((Product filteredProduct) ->
                color.equals(filteredProduct.getColor())).toList();

        return filtro;


    }

    private void validateParameters(Color color, List<Product> products) {

        if (color == null) throw new IllegalArgumentException("color should not be null");
        if (products == null) throw new IllegalArgumentException("product list should not be null");
    }

}
