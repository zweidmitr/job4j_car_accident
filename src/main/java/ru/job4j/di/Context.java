package ru.job4j.di;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * будем регистрироватть классы и отдовать проинициализированные объекты
 */
public class Context {
    /**
     * карта с объектами, в  ней мы будем хранить проиницизированные объекты
     */
    private Map<String, Object> els = new HashMap<>();

    /**
     * метод регистрации класса
     */
    public void reg(Class cl) {
        /**
         * проверяем количество конструкторов
         */
        Constructor[] constructors = cl.getConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors : " + cl.getCanonicalName());
        }
        /**
         * собираем аргументы конструктора и ищем уже зарегистрированные объекты, чтобы внедрить их в конструктор
         */
        Constructor con = constructors[0];
        List<Object> args = new ArrayList<>();
        for (Class arg : con.getParameterTypes()) {
            if (!els.containsKey(arg.getCanonicalName())) {
                throw new IllegalStateException("Object doesn't found in context : " + arg.getCanonicalName());
            }
            args.add(els.get(arg.getCanonicalName()));
        }
        try {
            /**
             * создание объекта и добавление его в карту
             * con.newInstance = Constructor object to create and
             * initialize a new instance of the constructor's declaring class
             */
            els.put(cl.getCanonicalName(), con.newInstance(args.toArray()));
        } catch (Exception e) {
            throw new IllegalStateException("Can't create an instance of : " + cl.getCanonicalName(), e);
        }
    }

    /**
     * метод get возвращает проинициализированный объект
     */
    public <T> T get(Class<T> inst) {
        /**
         * через рефдиксию можно получить имя класса
         */
        return (T) els.get(inst.getCanonicalName());
    }
}
