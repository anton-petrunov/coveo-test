package io.github.anton_petrunov.coveo_test_wip;

import io.github.anton_petrunov.coveo_test_wip.model.City;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    final static String completeSearchBody = "London";
    final static String partialSearchBody = "ondo";

    public static void main(String[] args) throws IOException {
        List<City> cities = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("cities_canada-usa.tsv"))) {
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                cities.add(parse(bufferedReader.readLine()));
            }
        }

//        System.out.println(cities);
        List<City> citiesCompleteMathByName = cities.stream()
                .filter(city -> city.getName().matches(completeSearchBody)).collect(Collectors.toList());
        System.out.println("Quantity of cities with complete match " + citiesCompleteMathByName.size());

        List<City> citiesPartialMatchByName = cities.stream().filter(
                city -> city.getName().contains(partialSearchBody)).collect(Collectors.toList());
        System.out.println("Quantity of cities with partial match " + citiesPartialMatchByName.size());

        System.out.println(cities.stream().
                filter(city -> city.getName().matches("\\D{3}")).collect(Collectors.toList()));
    }

    public static City parse(final String line) {
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
