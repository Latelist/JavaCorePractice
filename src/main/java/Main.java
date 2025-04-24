import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>(List.of("Shut up", "and", "take", "my money"));
        ArrayList<Integer> integers = new ArrayList<>(List.of(9,2,4,8));
        ArrayList<Object> objects = new ArrayList<>(List.of("Блэкджек", "и", 991823));

        ArrayList<String> filteredStrings = FilterClass.process(strings, new FilterClass<>());
        ArrayList<Integer> filteredIntegers = FilterClass.process(integers, new FilterClass<>());
        ArrayList<Object> filteredObjects = FilterClass.process(objects, new FilterClass<>());

        System.out.println(filteredStrings);
        System.out.println(filteredIntegers);
        System.out.println(filteredObjects);

        ArrayList<String> words = new ArrayList<>(List.of("Базар", "Вокзал", "Базар-вокзал", "Базар"));
        Map<String, Long> mappedWords = ArrayMapper.arrayToMap(words);
        System.out.println(mappedWords);

        ArrayList<Integer> numbers = new ArrayList<>(List.of(12, 59, 4, -8, -8));
        Map<Integer, Long> mappedNumbers = ArrayMapper.arrayToMap(numbers);
        System.out.println(mappedNumbers);
    }
}