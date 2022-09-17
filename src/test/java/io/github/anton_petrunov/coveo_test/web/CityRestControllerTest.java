package io.github.anton_petrunov.coveo_test.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.anton_petrunov.coveo_test.service.CityService;
import io.github.anton_petrunov.coveo_test.to.CityTo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-mvc.xml"})
class CityRestControllerTest {

    private static final String REST_URL = CityRestController.REST_URL + "?q=";

    private final String nameFound = "Londo";
    private final String nameNotFound = "Samara";
    private final String latitudeParameter = "&latitude=";
    private final String longitudeParameter = "&longitude=";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    CityRestController controller;

    @Autowired
    CityService service;

    @Test
    void whenSearchOnlyByNameFoundThanNotEmpty() throws Exception {
        String expected = write(service.findScored(nameFound, null, null));
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + nameFound))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void whenSearchOnlyByNameNotFoundThanEmpty() throws Exception {
        String expected = write(service.findScored(nameNotFound, null, null));
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + nameNotFound))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(expected));
    }

    @Test
    void whenLongitudeAbsentThenEqualsSearchOnlyByName() throws Exception {
        String expected = write(service.findScored(nameFound, null, null));
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + nameFound + latitudeParameter + 47))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void whenLatitudeAbsentThenEqualsSearchOnlyByName() throws Exception {
        String expected = write(service.findScored(nameFound, null, null));
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + nameFound + longitudeParameter + -80))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void whenSearchByNameLatitudeAndLongitudeFoundThenNotEmpty() throws Exception {
        String expected = write(service.findScored(nameFound, 45F, -79F));
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + nameFound + latitudeParameter + 45 +
                        longitudeParameter + -79))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void whenSearchByNameLatitudeAndLongitudeNotFoundThenEmpty() throws Exception {
        String expected = write(service.findScored(nameNotFound, 45F, -79F));
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + nameNotFound + latitudeParameter + 45 +
                        longitudeParameter + -79))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }

    @Test
    void whenLongitudeNotNumberThenBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + nameFound + latitudeParameter + 40 +
                        longitudeParameter + "XXXX"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenLatitudeNotANumberThenBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + nameFound + latitudeParameter +
                        "YYYY" + latitudeParameter + -77))
                .andExpect(status().isBadRequest());
    }

    private static String write(List<CityTo> cityTos) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(Map.of("suggestions", cityTos));
    }

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

}