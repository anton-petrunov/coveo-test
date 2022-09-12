package io.github.anton_petrunov.coveo_test_wip;

import io.github.anton_petrunov.coveo_test_wip.repository.InMemoryCityRepository;
import io.github.anton_petrunov.coveo_test_wip.service.CityService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/spring-app.xml")) {
            InMemoryCityRepository cityRepository = applicationContext.getBean(InMemoryCityRepository.class);

            CityService service = applicationContext.getBean(CityService.class);

            service.findAndScore("Londo", 43.70011F, -79.4163F).forEach(System.out::println);
        }
    }
}
