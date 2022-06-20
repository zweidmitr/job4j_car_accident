package di;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        context.reg(Store.class);
        context.reg(ConsoleInput.class);
        context.reg(StartUI.class);

        StartUI ui  = context.get(StartUI.class);

        ui.add("Petr");
        ui.add("Ivan");
        ui.print();
    }
}
