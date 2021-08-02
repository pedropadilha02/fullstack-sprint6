package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.ProductsSortedByCode;

import java.util.List;

public class ProductsSortedByCodeMain {

    public static void main(String[] args) {
        var productsSortedByCode = new ProductsSortedByCode();
        List<Product> allProducts = ProductRepository.all();

        List<Product> sortedProducts = productsSortedByCode.sortByCode(allProducts);

        for (Product product : sortedProducts) {
            System.out.printf("%s - %s - %s %n", product.getCode(), product.getColor(), product.getName());
        }
    }


}
