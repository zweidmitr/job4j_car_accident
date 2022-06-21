package di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StartUI {
    @Autowired
    private Store store;
    @Autowired
    private ConsoleInput consoleInput;

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
