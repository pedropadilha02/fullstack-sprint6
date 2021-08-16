package br.com.rchlo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.rchlo.domain.Color;
import br.com.rchlo.domain.Product;

public class ProductsColorReport {

    private Map<Color, Integer> quantityPerColor = new HashMap<>();

    public List<Product> filter(List<Product> productList ){

        validateParameters(productList);

        Color[] colorsArray = Color.values();

        for (Color color : colorsArray) {
            this.quantityPerColor.put(color, 0);
        }

        productList.forEach(product -> {
            Integer quantity = this.quantityPerColor.get(product.getColor());

            Color color = product.getColor();

            this.quantityPerColor.put(color, quantity + 1);

        });

        showQuantityPerColor();

        return productList;
    };


    private void showQuantityPerColor() {

        this.quantityPerColor.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    private void validateParameters(List<Product> products) {

        if (products == null) throw new IllegalArgumentException("product list should not be null");
    }

}