import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterClass<T>{

    Filter<T> filter = t -> {
        System.out.println("Обрабатываю объект: " + t.toString());
        return t;
    };
//    @Override
//    public T apply(T o) {
//        System.out.println("Обрабатываю объект: " + o.toString());
//        return o;
//    }

    public static <T> ArrayList<T> process(ArrayList<T> list, FilterClass<T> filterClass) {
        return list.stream()
                .map(filterClass.filter::apply)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}