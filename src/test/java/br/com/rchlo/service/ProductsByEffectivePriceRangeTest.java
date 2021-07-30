package br.com.rchlo.service;

import br.com.rchlo.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static br.com.rchlo.mother.ProductMother.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductsByEffectivePriceRangeTest {

    private ProductsByEffectivePriceRange productsByEffectivePriceRange;

    @BeforeEach
    void setUp() {
        productsByEffectivePriceRange = new ProductsByEffectivePriceRange();
    }

    @Test
    void shouldReturnOnlyProductsInTheRange() {
        List<Product> products = List.of(aDiscountedTShirt(), anExpensiveJacket(), aCheapTankTop());
        BigDecimal minimumPrice = new BigDecimal("30.0");
        BigDecimal maximumPrice = new BigDecimal("40.0");

        List<Product> filteredProducts = productsByEffectivePriceRange.filter(minimumPrice, maximumPrice, products);

        assertEquals(1, filteredProducts.size());

        Product product = filteredProducts.get(0);
        assertEquals(14124998L, product.getCode());
        assertEquals("Camiseta Infantil Manga Curta Super Mario", product.getName());
    }

    @Test
    void shouldWorkWithAnExactPrice() {
        List<Product> products = List.of(aCheapTankTop());
        BigDecimal minimumPrice = new BigDecimal("29.90");
        BigDecimal maximumPrice = new BigDecimal("29.90");

        List<Product> filteredProducts = productsByEffectivePriceRange.filter(minimumPrice, maximumPrice, products);

        assertEquals(1, filteredProducts.size());

        Product product = filteredProducts.get(0);
        assertEquals(14040174L, product.getCode());
        assertEquals("Regata Infantil Mario Bros", product.getName());
    }

    @Test
    void shouldWorkWithAnDiscounts() {
        List<Product> products = List.of(aDiscountedTShirt());
        BigDecimal minimumPrice = new BigDecimal("34.90");
        BigDecimal maximumPrice = new BigDecimal("34.90");

        List<Product> filteredProducts = productsByEffectivePriceRange.filter(minimumPrice, maximumPrice, products);

        assertEquals(1, filteredProducts.size());

        Product product = filteredProducts.get(0);
        assertEquals(14124998L, product.getCode());
        assertEquals("Camiseta Infantil Manga Curta Super Mario", product.getName());
    }

}