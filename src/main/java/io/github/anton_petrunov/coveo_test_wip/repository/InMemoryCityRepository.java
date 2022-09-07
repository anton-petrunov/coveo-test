package io.github.anton_petrunov.coveo_test_wip.repository;

import io.github.anton_petrunov.coveo_test_wip.model.City;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.anton_petrunov.coveo_test_wip.Main.parse;

@Repository
public class InMemoryCityRepository {

    private static final String TSV_PATH = "cities_canada-usa.tsv";

    private final List<City> cities = new ArrayList<>();

    {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(TSV_PATH))) {
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                cities.add(parse(bufferedReader.readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<City> getCitiesPartialMatchingNames(String searchBody) {
        return cities.stream()
                .filter(city -> city.getName().toLowerCase().contains(searchBody.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<City> getCitiesCompleteMatchingNames(String searchBody) {
        return cities.stream()
                .filter(city -> city.getName().toLowerCase().matches(searchBody.toLowerCase()))
                .collect(Collectors.toList());
    }
}
