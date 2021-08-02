package br.com.rchlo.service;

import br.com.rchlo.domain.Color;
import br.com.rchlo.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.rchlo.mother.ProductMother.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductsByColorTest {

    private ProductsByColor productsByColor;

    @BeforeEach
    void setUp() {
        productsByColor = new ProductsByColor();
    }

    @Test
    void shouldReturnOnlyTheCorrectColor() {
        List<Product> products = List.of(aDiscountedTShirt(), anExpensiveJacket(), aCheapTankTop());

        List<Product> filteredProducts = productsByColor.filter(Color.WHITE, products);

        assertThat(filteredProducts)
                .hasSize(2)
                .extracting(Product::getColor)
                .anyMatch(Color.WHITE::equals);
    }

    @Test
    void shouldAcceptAnEmptyList() {
        List<Product> emptyProducts = List.of();

        List<Product> filteredProducts = productsByColor.filter(Color.WHITE, emptyProducts);

        assertEquals(0, filteredProducts.size());
    }

    @Test
    void shouldNotAcceptANullCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Product> emptyProducts = List.of();
            productsByColor.filter(null, emptyProducts);
        });
    }

    @Test
    void shouldNotAcceptANullList() {
        assertThrows(IllegalArgumentException.class, () -> {
            productsByColor.filter(Color.WHITE, null);
        });
    }

}