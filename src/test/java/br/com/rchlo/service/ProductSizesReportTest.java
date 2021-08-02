package br.com.rchlo.service;

import br.com.rchlo.domain.Product;
import br.com.rchlo.domain.Size;
import br.com.rchlo.dto.ProductSizesReportResult;
import br.com.rchlo.dto.ProductSizesReportResult.ProductSizesReportResultItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static br.com.rchlo.mother.ProductMother.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductSizesReportTest {

    private ProductSizesReport productSizesReport;

    @BeforeEach
    void setUp() {
        productSizesReport = new ProductSizesReport();
    }

    @Test
    void shouldCalculateTheCorrectQuantities() {
        List<Product> products = List.of(aDiscountedTShirt(), anExpensiveJacket(), aCheapTankTop());

        ProductSizesReportResult report = productSizesReport.report(products);

        Assertions.assertThat(report)
                .containsExactlyInAnyOrder(
                        new ProductSizesReportResultItem(Size.SMALL, List.of(aDiscountedTShirt(), aCheapTankTop())),
                        new ProductSizesReportResultItem(Size.MEDIUM, List.of(aDiscountedTShirt(), aCheapTankTop())),
                        new ProductSizesReportResultItem(Size.LARGE, List.of(anExpensiveJacket(), aCheapTankTop())),
                        new ProductSizesReportResultItem(Size.EXTRA_LARGE, List.of(anExpensiveJacket()))
                );
    }

    @Test
    void shouldBeAnEmptyListForAllSizesWhenThereAreNoProducts() {
        List<Product> products = List.of();

        ProductSizesReportResult report = productSizesReport.report(products);

        Assertions.assertThat(report)
                .extracting(ProductSizesReportResultItem::getProducts)
                .isNotEmpty()
                .allMatch(Objects::nonNull)
                .allMatch(List::isEmpty);
    }

    @Test
    void shouldNotAcceptANullList() {
        assertThrows(IllegalArgumentException.class, () -> {
            productSizesReport.report(null);
        });
    }

}