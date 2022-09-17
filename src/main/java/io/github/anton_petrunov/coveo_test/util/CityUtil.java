package io.github.anton_petrunov.coveo_test.util;

import io.github.anton_petrunov.coveo_test.model.City;
import io.github.anton_petrunov.coveo_test.to.CityTo;

import java.util.List;
import java.util.stream.Collectors;

public class CityUtil {

    private static CityTo createTo(City city) {
        return new CityTo(
                city.getGeoNameId(),
                city.getName(),
                city.getLatitude(),
                city.getLongitude(),
                city.getCountry(),
                city.getAdmin1()
        );
    }

    public static List<CityTo> createTos(List<City> cities) {
        return cities.stream()
                .map(CityUtil::createTo)
                .collect(Collectors.toList());
    }
}
