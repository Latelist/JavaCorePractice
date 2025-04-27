package students;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStream {
    public static Map<String, Double> averageGrades(List<Student> list) {
        return list.parallelStream()
                .flatMap(student -> student.getGrades().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingInt(Map.Entry::getValue)
                ));
    }
}
