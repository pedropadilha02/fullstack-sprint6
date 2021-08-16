package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.ProductsSizeReport;

import java.util.List;

public class ProductsSizeReportMain {
    public static void main(String[] args) {
        var ProductsSizeReportFilter = new ProductsSizeReport();
        List<Product> allProducts = ProductRepository.all();

        ProductsSizeReportFilter.report(allProducts);


    }
}
