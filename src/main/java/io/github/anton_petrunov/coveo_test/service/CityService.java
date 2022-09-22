package io.github.anton_petrunov.coveo_test.service;

import io.github.anton_petrunov.coveo_test.repository.InMemoryCityRepository;
import io.github.anton_petrunov.coveo_test.to.CityTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.anton_petrunov.coveo_test.util.CityUtil.createTos;
import static io.github.anton_petrunov.coveo_test.util.DistanceUtil.calculateDistanceKm;
import static io.github.anton_petrunov.coveo_test.util.ScoreUtil.*;

@Service
public class CityService {

    @Autowired
    private InMemoryCityRepository repository;

    public List<CityTo> findScored(String name, Float searchLatitude, Float searchLongitude) {
        List<CityTo> cities = getCityTos(name);
        List<CityTo> citiesScoredByName = getScoredByName(cities, name);

        if (searchLatitude == null || searchLongitude == null) {
            return getScoredAndOrdered(citiesScoredByName);
        } else {
            List<CityTo> citiesScoredByNameAndDistance = getScoredByDistance(citiesScoredByName,
                    searchLatitude, searchLongitude);
            return getScoredAndOrdered(citiesScoredByNameAndDistance);
        }
    }

    private List<CityTo> getCityTos(String name) {
        return createTos(repository.findByNamePart(name));
    }

    private List<CityTo> getScoredByName(List<CityTo> cityTos, String name) {
        int completeMatchesQuantity = (int) cityTos.stream()
                .filter(cityTo -> cityTo.getShortName().toLowerCase().matches(name.toLowerCase()))
                .count();

        Integer partialMatchesQuantity = cityTos.size() - completeMatchesQuantity;

        for (CityTo cityTo : cityTos) {
            if (cityTo.getShortName().equalsIgnoreCase(name)) {
                cityTo.setNameScore(1F);
            } else {
                cityTo.setNameScore(getNameScore(partialMatchesQuantity));
            }
        }
        return cityTos;
    }

    private List<CityTo> getScoredByDistance(List<CityTo> cityTos, Float searchLatitude, Float searchLongitude) {
        cityTos.forEach(
                cityTo -> cityTo.setDistanceKm(calculateDistanceKm(
                        searchLatitude,
                        searchLongitude,
                        cityTo.getLatitude(),
                        cityTo.getLongitude()
                )));
        cityTos.forEach(
                cityTo -> cityTo.setDistanceScore(getDistanceScore(cityTo.getDistanceKm())));
        return cityTos;
    }

    private List<CityTo> getScoredAndOrdered(List<CityTo> cityTos) {
        cityTos.forEach(
                cityTo -> cityTo.setScore(
                        getScore(cityTo.getNameScore(), cityTo.getDistanceScore())));
        return cityTos.stream()
                .sorted(Comparator.comparing(CityTo::getScore).reversed())
                .collect(Collectors.toList());
    }
}
