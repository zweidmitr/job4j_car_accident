package ru.job4j.accident;

import org.springframework.web.filter.CharacterEncodingFilter;
import ru.job4j.accident.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

/**
 * Когда tomcat загружает наше приложение, он ищет класс, который расширяет WebApplicationInitializer.
 * Tomcat создает контекст Spring и загружает DispatcherServlet.
 * DispatcherServlet будет обрабатывать все запросы. Он доступен по адресу, указанному в addMapping().
 */

public class WebInit implements WebApplicationInitializer {
    /**
     * добавим фильтр для кодировки.
     * CharacterEncodingFilter будет преобразовывать текст в кодировку UTF-8.
     * Без этого кириллиц будет выглядеть крякозаброй.
     */
    public void onStartup(ServletContext servletCxt) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(WebConfig.class);
        ac.refresh();
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        FilterRegistration.Dynamic encoding = servletCxt.addFilter("encoding",filter);
        encoding.addMappingForUrlPatterns(null,false,"/*");
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
