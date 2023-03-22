package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.v1.BeerDtoV1;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
public interface BeerServiceV2 {
    BeerDtoV1 getBeerById(UUID beerId);

    BeerDtoV1 saveNewBeer(BeerDtoV1 beerDto);

    void updateBeer(UUID beerId, BeerDtoV1 beerDto);

    void deleteById(UUID beerId);
}
