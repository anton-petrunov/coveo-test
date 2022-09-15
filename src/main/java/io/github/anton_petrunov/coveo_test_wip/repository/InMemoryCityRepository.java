package io.github.anton_petrunov.coveo_test_wip.repository;

import io.github.anton_petrunov.coveo_test_wip.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryCityRepository {

    private final List<City> cities = new ArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(InMemoryCityRepository.class);

    @PostConstruct
    public void init() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                "D:\\Java\\ProjectsForUltimateIDEA\\coveo-test-wip\\src\\main\\webapp\\WEB-INF\\cities_canada-usa.tsv"))) {
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                cities.add(parse(bufferedReader.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<City> findByNamePart(String name) {
        log.info("findByNamePart '{}'", name);
        return cities.stream()
                .filter(city -> city.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    private City parse(final String line) {
        String[] elements = line.split("\t");
        return new City(
                Integer.parseInt(elements[0]),
                elements[1],
                Float.parseFloat(elements[4]),
                Float.parseFloat(elements[5]),
                elements[8],
                elements[10]);
    }
}
