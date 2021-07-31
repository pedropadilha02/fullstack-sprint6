package br.com.rchlo.service;

import br.com.rchlo.domain.Product;
import br.com.rchlo.dto.ProductColorsReportResult;

import java.util.List;

public class ProductColorsReport {

    public ProductColorsReportResult report(List<Product> products) {
        var reportResult = new ProductColorsReportResult();
        products.forEach(product -> reportResult.addColor(product.getColor()));
        return reportResult;
    }

}
