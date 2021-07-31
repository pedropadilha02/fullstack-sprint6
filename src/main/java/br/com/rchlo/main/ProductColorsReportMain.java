package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.dto.ProductColorsReportResult;
import br.com.rchlo.dto.ProductColorsReportResult.ProductColorsReportResultItem;
import br.com.rchlo.service.ProductColorsReport;

import java.util.List;

public class ProductColorsReportMain {

    public static void main(String[] args) {
        var productColorsReport = new ProductColorsReport();
        List<Product> allProducts = ProductRepository.all();

        ProductColorsReportResult reportResult = productColorsReport.report(allProducts);
        for (ProductColorsReportResultItem reportItem : reportResult) {
            System.out.printf("%s: %s%n", reportItem.getColor(), reportItem.getQuantity());
        }
    }

}
