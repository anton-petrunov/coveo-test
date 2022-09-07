package io.github.anton_petrunov.coveo_test_wip;

import io.github.anton_petrunov.coveo_test_wip.model.City;
import io.github.anton_petrunov.coveo_test_wip.util.DistanceUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    final static String completeSearchBody = "Londo";
    final static String partialSearchBody = "ondo";
    final static float searchPointLatitude = 43.70011F;
    final static float searchPointLongitude = -79.4163F;

    public static void main(String[] args) throws IOException {
        List<City> cities = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("cities_canada-usa.tsv"))) {
            bufferedReader.readLine();
            while (bufferedReader.ready()) {
                cities.add(parse(bufferedReader.readLine()));
            }
        }

        System.out.println("Total cities quantity " + cities.size());

        List<City> citiesCompleteMathByName = cities.stream()
                .filter(city -> city.getName().toLowerCase().matches(completeSearchBody.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("Quantity of cities with complete match " + citiesCompleteMathByName.size());
        System.out.println(citiesCompleteMathByName);

        List<City> citiesPartialMatchByName = cities.stream()
                .filter(city -> city.getName().toLowerCase().contains(completeSearchBody.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("Quantity of cities with partial match " + citiesPartialMatchByName.size());
        System.out.println(citiesPartialMatchByName);

        System.out.println(citiesPartialMatchByName.stream()
                .map(city -> DistanceUtil.calculateDistanceKm(
                        searchPointLatitude, searchPointLongitude, city.getLatitude(), city.getLongitude()))
                .collect(Collectors.toList()));

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
