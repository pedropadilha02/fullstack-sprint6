package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.dto.ProductSizesReportResult;
import br.com.rchlo.dto.ProductSizesReportResult.ProductSizesReportResultItem;
import br.com.rchlo.service.ProductSizesReport;

import java.util.List;

public class ProductSizesReportMain {

    public static void main(String[] args) {
        var productSizesReport = new ProductSizesReport();
        List<Product> allProducts = ProductRepository.all();

        ProductSizesReportResult reportResult = productSizesReport.report(allProducts);
        for (ProductSizesReportResultItem reportItem : reportResult) {
            System.out.printf("%n%s%n", reportItem.getSize());
            reportItem.getProducts()
                    .forEach(product -> System.out.printf("%s - %s %n", product.getCode(), product.getName()));
        }

    }

}
