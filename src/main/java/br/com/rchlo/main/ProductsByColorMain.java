package br.com.rchlo.main;

import br.com.rchlo.data.ProductRepository;
import br.com.rchlo.domain.Color;
import br.com.rchlo.domain.Product;
import br.com.rchlo.service.filter.Filter;

import java.util.List;

public class ProductsByColorMain {

    public static void main(String[] args) {

        List<Product> allProducts = ProductRepository.all();

        Color color = Color.WHITE;

        var filter = new Filter();
        List<Product> filteredProducts = filter.execute(product -> color.equals(product.getColor()), allProducts);

        for (Product product : filteredProducts) {
            System.out.printf("%s - %s - %s %n", product.getCode(), product.getColor(), product.getName());
        }

    }

}
