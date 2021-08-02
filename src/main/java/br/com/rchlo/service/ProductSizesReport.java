package br.com.rchlo.service;

import br.com.rchlo.domain.Product;
import br.com.rchlo.dto.ProductSizesReportResult;

import java.util.List;

public class ProductSizesReport {

    public ProductSizesReportResult report(List<Product> products) {
        validateParameters(products);

        var reportResult = new ProductSizesReportResult();
        products.forEach(reportResult::addProduct);
        return reportResult;
    }

    private void validateParameters(List<Product> products) {
        if (products == null) throw new IllegalArgumentException("product list should not be null");
    }

}
