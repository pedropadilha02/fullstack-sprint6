package br.com.rchlo.main;

import java.util.List;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.ProductsColorReport;


public class ProductsColorReportMain {

    public static void main(String[] args) {
        var productsByColorFilter = new ProductsColorReport();
        List<Product> allProducts = ProductRepository.all();

        productsByColorFilter.filter(allProducts);

    }
}
