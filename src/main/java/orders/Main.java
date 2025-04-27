package orders;

import java.util.List;

import static java.util.stream.Collectors.toMap;

public class Main {
        public static void main(String[] args) {
            List<Order> orders = List.of(
                    new Order("Laptop", 1200.0),
                    new Order("Smartphone", 800.0),
                    new Order("Laptop", 1500.0),
                    new Order("Tablet", 500.0),
                    new Order("Smartphone", 900.0),
                    new Order("твоя жопа", 1.0)
            );

            Stream.group(orders);
        }
    }
