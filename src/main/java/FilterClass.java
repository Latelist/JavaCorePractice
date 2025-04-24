import java.util.ArrayList;

public class FilterClass<T> implements Filter<T>{
    @Override
    public T apply(T o) {
        System.out.println("Обрабатываю объект: " + o.toString());
        return o;
    }

    public static <T> ArrayList<T> filter(ArrayList<T> list, FilterClass<T> filterClass) {
        ArrayList<T> resultList = new ArrayList<>();
        for (T o : list) {
            resultList.add(filterClass.apply(o));
        }
        return resultList;
    }
}