import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArrayMapper<T> {
    public static <T> Map<T, Long> arrayToMap(ArrayList<T> arrayList){
        Map<T, Long> map = arrayList.stream().
                collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        return map;
    }
}
