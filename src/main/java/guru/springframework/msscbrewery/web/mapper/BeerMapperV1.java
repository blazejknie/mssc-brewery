package guru.springframework.msscbrewery.web.mapper;

import guru.springframework.msscbrewery.domain.Beer;
import guru.springframework.msscbrewery.web.model.v1.BeerDtoV1;
import org.mapstruct.Mapper;

@Mapper(uses = DateTimeMapper.class)
public interface BeerMapperV1 {
    BeerDtoV1 beerToDto(Beer beer);

    Beer dtoToBeer(BeerDtoV1 dto);
}
