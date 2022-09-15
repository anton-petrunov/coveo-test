package io.github.anton_petrunov.coveo_test_wip.service;

import io.github.anton_petrunov.coveo_test_wip.to.CityTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(locations = {"classpath:spring/spring-app.xml"})
class CityServiceTest {

    private final String nameNotFound = "Izhevsk";
    private final String nameFound = "aShing";

    @Autowired
    CityService service;

    @Test
    void whenSearchOnlyByNameNotFoundThenEmpty() {
        List<CityTo> actual = service.findScored(nameNotFound, null, null);
        assertThat(actual).isEmpty();
    }

    @Test
    void whenSearchOnlyByNameFoundThanNotEmpty() {
        List<CityTo> actual = service.findScored(nameFound, null, null);
        assertThat(actual).isNotEmpty().isSortedAccordingTo(Comparator.comparing(CityTo::getScore).reversed());
    }

    @Test
    void whenLongitudeNullThenEqualsSearchOnlyByName() {
        List<CityTo> foundOnlyByName = service.findScored(nameFound, null, null);
        List<CityTo> longitudeNull = service.findScored(nameFound, 44F, null);
        assertThat(longitudeNull).usingRecursiveFieldByFieldElementComparator().isEqualTo(foundOnlyByName);
    }

    @Test
    void whenLatitudeNullThenEqualsSearchOnlyByName() {
        List<CityTo> foundOnlyByName = service.findScored(nameFound, null, -79F);
        List<CityTo> latitudeNull = service.findScored(nameFound, null, -79F);
        assertThat(latitudeNull).usingRecursiveFieldByFieldElementComparator().isEqualTo(foundOnlyByName);
    }

    @Test
    void whenSearchByNameLatitudeAndLongitudeFoundThenNotEmpty() {
        List<CityTo> actual = service.findScored(nameFound, 44F, -90F);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void whenSearchByNameLatitudeAndLongitudeNotFoundThenEmpty() {
        List<CityTo> actual = service.findScored(nameNotFound, 47F, -70F);
        assertThat(actual).isEmpty();
    }
}