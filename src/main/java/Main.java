import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        //Задание 1 из середины курса на отрисовку таймстампа
        LocalDT localDT = new LocalDT();
        localDT.setTimestamp();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(localDT);
        System.out.println(json);

        //Задание 2 из конца курса на реализацию своего StringBuilder
        MyStringBuilder myStringBuilder = new MyStringBuilder();
        myStringBuilder.append("Разорвись");
        myStringBuilder.append("на британский");
        myStringBuilder.delete(1, 3);
        myStringBuilder.insert(5, "флаг");
        System.out.println(myStringBuilder);
        myStringBuilder.undo();
        System.out.println(myStringBuilder);
    }
}
