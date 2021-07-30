package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.dto.ProductsReportResult;
import br.com.rchlo.dto.ProductsReportResult.ProductsReportResultItem;
import br.com.rchlo.service.ProductColorsReport;

import java.util.List;

public class ProductColorsReportMain {

    public static void main(String[] args) {
        var productColorsReport = new ProductColorsReport();
        List<Product> allProducts = ProductRepository.all();

        ProductsReportResult reportResult = productColorsReport.report(allProducts);
        for (ProductsReportResultItem reportItem : reportResult) {
            System.out.printf("%s: %s%n", reportItem.getColor(), reportItem.getQuantity());
        }
    }

}
