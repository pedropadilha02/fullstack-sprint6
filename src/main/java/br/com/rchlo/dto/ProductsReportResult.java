package br.com.rchlo.dto;

import br.com.rchlo.domain.Color;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class ProductsReportResult implements Iterable<ProductsReportResult.ProductsReportResultItem> {

    private final Map<Color, Integer> quantitiesByColor;

    public ProductsReportResult() {
        this.quantitiesByColor = Arrays.stream(Color.values())
                .collect(Collectors.toMap(identity(), color -> 0));
    }

    public void addColor(Color color) {
        this.quantitiesByColor.compute(color, (c, quantity) -> quantity+1);
    }

    public static class ProductsReportResultItem {

        private final Color color;
        private final int quantity;

        public ProductsReportResultItem(Color color, int quantity) {
            this.color = color;
            this.quantity = quantity;
        }

        public Color getColor() {
            return color;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    @Override
    public Iterator<ProductsReportResultItem> iterator() {
        return new Iterator<>() {
            private final Iterator<Color> iterator = quantitiesByColor.keySet().iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ProductsReportResultItem next() {
                Color color = iterator.next();
                Integer quantity = ProductsReportResult.this.quantitiesByColor.get(color);
                return new ProductsReportResultItem(color, quantity);
            }
        };
    }
}
