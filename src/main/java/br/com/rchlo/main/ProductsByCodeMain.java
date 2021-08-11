package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.filter.Filter;

import java.util.List;

public class ProductsByCodeMain {

    public static void main(String[] args) {

        List<Product> allProducts = ProductRepository.all();

        Long code = 14040174L;

        var filter = new Filter();

        List<Product> filteredProducts = filter.execute(product -> code.equals(product.getCode()), allProducts);

        for (Product product : filteredProducts) {
            System.out.printf("%s - %s %n", product.getCode(), product.getName());
        }

    }

}
