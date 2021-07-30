package br.com.rchlo.service;

import br.com.rchlo.domain.Product;
import br.com.rchlo.dto.ProductsReportResult;

import java.util.List;

public class ProductColorsReport {

    public ProductsReportResult report(List<Product> products) {
        var reportResult = new ProductsReportResult();
        products.forEach(product -> reportResult.addColor(product.getColor()));
        return reportResult;
    }

}
