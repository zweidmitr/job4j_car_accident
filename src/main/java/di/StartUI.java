package di;

import org.springframework.stereotype.Component;

@Component
public class StartUI {
    private Store store;
    private ConsoleInput consoleInput;

    public StartUI(Store store, ConsoleInput consoleInput) {
        this.consoleInput = consoleInput;
        this.store = store;
    }

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        var s = consoleInput.askStr("are you sure?");
        if (s.toLowerCase().equals("yes")) {
            for (String value : store.getAll()) {
                System.out.println(value);
            }
        } else {
            System.out.println("bye");
        }
    }
}
