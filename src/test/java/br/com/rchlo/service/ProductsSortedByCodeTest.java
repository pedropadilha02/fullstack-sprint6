package br.com.rchlo.service;

import br.com.rchlo.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static br.com.rchlo.mother.ProductMother.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductsSortedByCodeTest {

    private ProductsSortedByCode productsSortedByCode;

    @BeforeEach
    void setUp() {
        productsSortedByCode = new ProductsSortedByCode();
    }

    @Test
    void shouldSortProductsByCode() {
        List<Product> products = List.of(aDiscountedTShirt(), anExpensiveJacket(), aCheapTankTop());

        List<Product> sortedProducts = productsSortedByCode.sortByCode(products);

        assertThat(sortedProducts)
                .hasSize(3)
                .isSortedAccordingTo(Comparator.comparing(Product::getCode));
    }

    @Test
    void shouldSortAnEmptyList() {
        List<Product> products = List.of();

        List<Product> sortedProducts = productsSortedByCode.sortByCode(products);

        assertThat(sortedProducts)
                .isEmpty();
    }

    @Test
    void shouldNotAcceptANullList() {
        assertThrows(IllegalArgumentException.class, () -> productsSortedByCode.sortByCode(null));
    }
}
