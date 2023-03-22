package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.v1.BeerDtoV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV1 getBeerById(UUID beerId) {
        return BeerDtoV1.builder().id(UUID.randomUUID())
                        .beerName("Galaxy Cat")
                        .beerStyle("Pale Ale")
                        .build();
    }

    @Override
    public BeerDtoV1 saveNewBeer(BeerDtoV1 beerDto) {
        return BeerDtoV1.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDtoV1 beerDto) {
        log.debug("update beer: " + beerDto);
    }

    @Override
    public void deleteById(UUID beerId) {
      log.debug("deleting beer....");
    }
}
