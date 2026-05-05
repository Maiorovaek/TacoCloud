//package tacos;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
////Контроллер HomeController можно также заменить несколькими строками в классе конфигурации, после чего удалить HomeController,
//// и приложение будет действовать так же, как раньше. Единственное, что при этом потребуется изменить,
//// – вернуться к классу HomeControllerTest, представленному в главе 1, и удалить ссылку на HomeController из аннотации @WebMvcTest,
//// чтобы тестовый класс компилировался без ошибок.
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("home");
//    }
//
//}
