package io.github.anton_petrunov.coveo_test_wip;

import io.github.anton_petrunov.coveo_test_wip.repository.InMemoryCityRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static io.github.anton_petrunov.coveo_test_wip.util.CityUtil.getTos;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-app.xml")) {
            InMemoryCityRepository cityRepository = applicationContext.getBean(InMemoryCityRepository.class);
            System.out.println(cityRepository.getCitiesPartialMatchingNames("London").size());

            System.out.println(cityRepository.getCitiesCompleteMatchingNames("London").size());
            System.out.println(getTos(
                    cityRepository.getCitiesPartialMatchingNames("London"),
                    43.70011F,
                    -79.4163F));
        }
    }
}
