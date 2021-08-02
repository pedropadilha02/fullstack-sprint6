package br.com.rchlo.dto;

import br.com.rchlo.domain.Color;

import java.util.Iterator;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class ProductColorsReportResult implements Iterable<ProductColorsReportResult.ProductColorsReportResultItem> {

    private final Map<Color, Integer> quantityByColor;

    public ProductColorsReportResult() {
        this.quantityByColor = stream(Color.values())
                .collect(toMap(identity(), color -> 0));
    }

    public void addColor(Color color) {
        this.quantityByColor.compute(color, (c, quantity) -> quantity+1);
    }

    public static class ProductColorsReportResultItem {

        private final Color color;
        private final int quantity;

        public ProductColorsReportResultItem(Color color, int quantity) {
            this.color = color;
            this.quantity = quantity;
        }

        public Color getColor() {
            return color;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProductColorsReportResultItem that = (ProductColorsReportResultItem) o;

            if (quantity != that.quantity) return false;
            return color == that.color;
        }

        @Override
        public int hashCode() {
            int result = color != null ? color.hashCode() : 0;
            result = 31 * result + quantity;
            return result;
        }

        @Override
        public String toString() {
            return "ProductColorsReportResultItem{" +
                    "color=" + color +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    @Override
    public Iterator<ProductColorsReportResultItem> iterator() {
        return new Iterator<>() {
            private final Iterator<Color> iterator = quantityByColor.keySet().iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public ProductColorsReportResultItem next() {
                Color color = iterator.next();
                Integer quantity = ProductColorsReportResult.this.quantityByColor.get(color);
                return new ProductColorsReportResultItem(color, quantity);
            }
        };
    }
}
