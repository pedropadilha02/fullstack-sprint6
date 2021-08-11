package br.com.rchlo.service.filter;

import br.com.rchlo.domain.Product;

public interface Criteria {

    public abstract boolean verify(Product product);

}
