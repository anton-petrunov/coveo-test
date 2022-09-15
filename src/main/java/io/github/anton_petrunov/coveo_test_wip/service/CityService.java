package io.github.anton_petrunov.coveo_test_wip.service;

import io.github.anton_petrunov.coveo_test_wip.repository.InMemoryCityRepository;
import io.github.anton_petrunov.coveo_test_wip.to.CityTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.anton_petrunov.coveo_test_wip.util.CityUtil.createTos;
import static io.github.anton_petrunov.coveo_test_wip.util.DistanceUtil.calculateDistanceKm;
import static io.github.anton_petrunov.coveo_test_wip.util.ScoreUtil.*;

@Service
public class CityService {

    @Autowired
    InMemoryCityRepository repository;

    public List<CityTo> findScored(String name, Float searchPointLatitude, Float searchPointLongitude) {

        if (searchPointLatitude == null || searchPointLongitude == null) {
            return getScoredAndOrdered(getScoredByName(getCityTos(name), name));
        } else {
            return getScoredAndOrdered(getScoredByDistance(
                    getScoredByName(getCityTos(name), name),
                    searchPointLatitude,
                    searchPointLongitude));
        }
    }

    private List<CityTo> getCityTos(String name) {
        return createTos(repository.findByNamePart(name));
    }

    private List<CityTo> getScoredByName(List<CityTo> cityTos, String name) {
        int completeMatchesQuantity = (int) cityTos.stream()
                .filter(cityTo -> cityTo.getShortName().matches(name))
                .count();

        Integer partialMatchesQuantity = cityTos.size() - completeMatchesQuantity;

        for (CityTo cityTo : cityTos) {
            if (cityTo.getShortName().equals(name)) {
                cityTo.setNameScore(1F);
            } else {
                cityTo.setNameScore(getNameScore(partialMatchesQuantity));
            }
        }
        return cityTos;
    }

    private List<CityTo> getScoredByDistance(List<CityTo> cityTos, Float searchPointLatitude,
                                             Float searchPointLongitude) {
        cityTos.forEach(
                cityTo -> cityTo.setDistanceKm(calculateDistanceKm(
                        searchPointLatitude,
                        searchPointLongitude,
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
