package io.github.anton_petrunov.coveo_test_wip.util;

import io.github.anton_petrunov.coveo_test_wip.model.City;
import io.github.anton_petrunov.coveo_test_wip.to.CityTo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.anton_petrunov.coveo_test_wip.util.DistanceUtil.calculateDistanceKm;

public class CityUtil {
    private static CityTo createTo(City city, float searchPointLatitude, float searchPointLongitude) {
        return new CityTo(
                city.getGeoNameId(),
                city.getName(),
                city.getLatitude(),
                city.getLongitude(),
                city.getCountry(),
                city.getAdmin1(),
                calculateDistanceKm(searchPointLatitude, searchPointLongitude,
                        city.getLatitude(), city.getLongitude())
        );
    }

    public static List<CityTo> getTos(List<City> cities, float searchPointLatitude, float searchPointLongitude) {
        return cities.stream()
                .map(city -> createTo(city, searchPointLatitude, searchPointLongitude))
                .sorted(Comparator.comparing(CityTo::getDistanceKm))
                .collect(Collectors.toList());
    }
}
