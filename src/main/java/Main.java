import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>(List.of("Shut up", "and", "take", "my money"));
        ArrayList<Integer> integers = new ArrayList<>(List.of(9,2,4,8));
        ArrayList<Object> objects = new ArrayList<>(List.of("Блэкджек", "и", 991823));

        ArrayList<String> filteredStrings = FilterClass.filter(strings, new FilterClass<>());
        ArrayList<Integer> filteredIntegers = FilterClass.filter(integers, new FilterClass<>());
        ArrayList<Object> filteredOjects = FilterClass.filter(objects, new FilterClass<>());

        System.out.println(filteredStrings);
        System.out.println(filteredIntegers);
        System.out.println(filteredOjects);
    }
}