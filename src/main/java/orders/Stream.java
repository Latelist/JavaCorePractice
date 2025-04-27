package orders;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream {

    //Группируйте заказы по продуктам. Для каждого продукта найдите общую стоимость всех заказов.
    public static void group(List<Order> list){
         list.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingDouble(Order :: getCost)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> System.out.println(entry.getKey() + " — $" + entry.getValue()));
    }
}
