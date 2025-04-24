import java.util.Stack;

public class MyStringBuilder {
    private StringBuilder stringBuilder;
    private Stack<String> history;

    public MyStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.history = new Stack<>();
    }

    public void append(Object object){
        takeASnapshot();
        this.stringBuilder.append(object);
    }

    public void delete(int start, int end) {
        takeASnapshot();
        this.stringBuilder.delete(start, end);
    }

    public void insert(int index, Object object) {
        takeASnapshot();
        this.stringBuilder.insert(index, object);
    }

    public void takeASnapshot() {
        history.push(this.stringBuilder.toString());
    }
    public void undo() {
        if (!history.isEmpty()) {
            this.stringBuilder = new StringBuilder(history.pop());
        }
    }

    public String toString() {
        return this.stringBuilder.toString();
    }
}
