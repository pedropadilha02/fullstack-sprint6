package br.com.rchlo.service.filter;

import br.com.rchlo.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    public List<Product> execute(Criteria criteria, List<Product> products) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (criteria.verify(product)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

}

// ByEffectivePriceRange seria um critério no if, que compara por FAIXA POR PREÇO
