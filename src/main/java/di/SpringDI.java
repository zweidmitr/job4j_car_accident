package di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Store.class);
        context.register(ConsoleInput.class);
        context.register(StartUI.class);
        context.refresh();

        StartUI ui = context.getBean(StartUI.class);
        ui.add("Petr");
        ui.add("Ivan");
        ui.print();
    }
}
