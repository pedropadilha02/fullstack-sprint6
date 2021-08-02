package br.com.rchlo.dto;

import br.com.rchlo.domain.Product;
import br.com.rchlo.domain.Size;

import java.util.*;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class ProductSizesReportResult implements Iterable<ProductSizesReportResult.ProductSizesReportResultItem> {

    private final Map<Size, List<Product>> productsBySize;

    public ProductSizesReportResult() {
        this.productsBySize = stream(Size.values())
                .collect(toMap(identity(), size -> new ArrayList<>()));
    }

    public void addProduct(Product product) {
        product.getAvailableSizes()
                .forEach(size -> this.productsBySize.get(size).add(product));
    }

    public static class ProductSizesReportResultItem {

        private final Size size;
        private final List<Product> products;

        public ProductSizesReportResultItem(Size size, List<Product> products) {
            this.size = size;
            this.products = List.copyOf(products);
        }

        public Size getSize() {
            return size;
        }

        public List<Product> getProducts() {
            return products;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProductSizesReportResultItem that = (ProductSizesReportResultItem) o;

            if (size != that.size) return false;
            return Objects.equals(products, that.products);
        }

        @Override
        public int hashCode() {
            int result = size != null ? size.hashCode() : 0;
            result = 31 * result + (products != null ? products.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "ProductSizesReportResultItem{" +
                    "size=" + size +
                    ", products=" + products +
                    '}';
        }
    }

    @Override
    public Iterator<ProductSizesReportResultItem> iterator() {
        return new Iterator<>() {

            private final Iterator<Size> iterator = productsBySize.keySet().iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ProductSizesReportResultItem next() {
                Size size = iterator.next();
                List<Product> products = productsBySize.get(size);
                return new ProductSizesReportResultItem(size, products);
            }
        };
    }

}
