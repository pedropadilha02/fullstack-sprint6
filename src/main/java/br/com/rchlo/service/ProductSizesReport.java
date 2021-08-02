package br.com.rchlo.service;

import br.com.rchlo.domain.Product;
import br.com.rchlo.dto.ProductColorsReportResult;
import br.com.rchlo.dto.ProductSizesReportResult;

import java.util.List;

public class ProductSizesReport {

    public ProductSizesReportResult report(List<Product> products) {
        var reportResult = new ProductSizesReportResult();
        products.forEach(product -> reportResult.addProduct(product));
        return reportResult;
    }
}
