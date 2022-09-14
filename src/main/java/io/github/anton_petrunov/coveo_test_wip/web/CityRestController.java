package io.github.anton_petrunov.coveo_test_wip.web;

import io.github.anton_petrunov.coveo_test_wip.service.CityService;
import io.github.anton_petrunov.coveo_test_wip.to.CityTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = CityRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CityRestController {

    static final String REST_URL = "/suggestions";

    @Autowired
    CityService service;

    @GetMapping
    public List<CityTo> find(@RequestParam String q, @RequestParam Float latitude, @RequestParam Float longitude) {
        return service.findAndScore(q, latitude, longitude);
    }
}
