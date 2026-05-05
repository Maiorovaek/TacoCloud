package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//1. @SpringBootConfiguration  -определяет этот класс как класс конфигурации,
//2. EnableAutoConfiguration- эта аннотация сообщает Spring Boot о необходимости автоматически настраивать
// любые компоненты, которые могут вам понадобиться ,
//3. @ComponentScan - включает сканирование компонентов. Меха- низм сканирования позволяет объявлять другие
// классы с анно- тациями, такими как @Component, @Controller и @Service, чтобы фреймворк Spring
// автоматически обнаруживал и регистрировал их как компоненты в контексте приложения Spring.
public class TacoCloudApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

    public void addViewController(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
