package io.github.anton_petrunov.coveo_test.repository;

import io.github.anton_petrunov.coveo_test.model.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(locations = {"classpath:spring/spring-app.xml"})
class InMemoryCityRepositoryTest {

    @Autowired
    InMemoryCityRepository repository;

    @Test
    void whenNamePartFoundThenNotEmpty() {
        String searchedName = "Asing";
        List<City> cities = repository.findByNamePart(searchedName);
        assertThat(cities).isNotEmpty();
    }

    @Test
    void whenNamePartNotFoundThenEmpty() {
        String searchedName = "London1";
        List<City> cities = repository.findByNamePart(searchedName);
        assertThat(cities).isEmpty();

    }
}