package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.v1.BeerService;
import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.controller.v2.BeerControllerV2;
import guru.springframework.msscbrewery.web.model.v1.BeerDtoV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({BeerControllerV2.class})
class BeerControllerTest {

    @MockBean
    BeerServiceV2 beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    BeerDtoV1 validBeer;

    @BeforeEach
    public void setUp() {
        validBeer = BeerDtoV1.builder()
                             .id(UUID.randomUUID())
                             .beerStyle("Lager")
                             .beerName("Some Beer")
                             .upc(123456789012L)
                             .build();
    }

    @Test
    void getBeer() throws Exception {
        when(beerService.getBeerById(any(UUID.class))).thenReturn(validBeer);
        mockMvc.perform(get("/api/v2/beer/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
               .andExpect(jsonPath("$.beerName", is(validBeer.getBeerName())));
    }

    @Test
    void handlePost() throws Exception {
        BeerDtoV1 savedBeer = BeerDtoV1.builder().beerName("New Beer").beerStyle("Pils").build();
        when(beerService.saveNewBeer(any(BeerDtoV1.class))).thenReturn(savedBeer);
        String newBeerAsJson = mapper.writeValueAsString(savedBeer);

        mockMvc.perform(post("/api/v2/beer/")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(newBeerAsJson))
               .andExpect(status().isCreated());
    }

    @Test
    void handleUpdate() throws Exception {
        BeerDtoV1 beer = validBeer;
        beer.setId(null);
        String beerAsJson = mapper.writeValueAsString(beer);

        mockMvc.perform(put("/api/v2/beer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON).content(beerAsJson))
               .andExpect(status().isNoContent());
    }
}