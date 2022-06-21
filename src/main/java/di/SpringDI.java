package di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("di");
        context.refresh();

        Store store = context.getBean(Store.class);
        store.add("Petr Arsentev");
        /**
         * Spring по умолчанию создает объекты в режиме singleton, то есть один объект на виртуальную машину.
         * Режим prototype будет создавать новый объект, каждый раз, когда мы просим его от контекста.
         */
        Store another = context.getBean(Store.class);
        another.getAll().forEach(System.out::println);

        StartUI ui = context.getBean(StartUI.class);
        ui.add("Petr");
        ui.add("Ivan");
        StartUI sUi = context.getBean(StartUI.class);
        sUi.print();

    }
}
