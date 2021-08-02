package br.com.rchlo.service;

import br.com.rchlo.domain.Color;
import br.com.rchlo.domain.Product;
import br.com.rchlo.dto.ProductColorsReportResult;
import br.com.rchlo.dto.ProductColorsReportResult.ProductColorsReportResultItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static br.com.rchlo.mother.ProductMother.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductColorsReportTest {

    private ProductColorsReport productColorsReport;

    @BeforeEach
    void setUp() {
        productColorsReport = new ProductColorsReport();
    }

    @Test
    void shouldCalculateTheCorrectQuantities() {
        List<Product> products = List.of(aDiscountedTShirt(), anExpensiveJacket(), aCheapTankTop());

        ProductColorsReportResult report = productColorsReport.report(products);

        Assertions.assertThat(report)
                .containsExactlyInAnyOrder(
                        new ProductColorsReportResultItem(Color.BLUE, 1),
                        new ProductColorsReportResultItem(Color.GRAY, 0),
                        new ProductColorsReportResultItem(Color.GREEN, 0),
                        new ProductColorsReportResultItem(Color.PINK, 0),
                        new ProductColorsReportResultItem(Color.RED, 0),
                        new ProductColorsReportResultItem(Color.WHITE, 2));
    }

    @Test
    void shouldCountAllZerosWhenListIsEmpty() {
        List<Product> products = List.of();

        ProductColorsReportResult report = productColorsReport.report(products);

        Assertions.assertThat(report)
                .extracting(ProductColorsReportResultItem::getQuantity)
                .isNotEmpty()
                .allMatch(quantity -> quantity == 0);
    }

    @Test
    void shouldNotAcceptANullList() {
        assertThrows(IllegalArgumentException.class, () -> {
            productColorsReport.report(null);
        });
    }

}