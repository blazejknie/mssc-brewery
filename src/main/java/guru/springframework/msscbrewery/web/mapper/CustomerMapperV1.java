package guru.springframework.msscbrewery.web.mapper;

import guru.springframework.msscbrewery.domain.Customer;
import guru.springframework.msscbrewery.web.model.v1.CustomerDtoV1;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapperV1 {
    Customer dtoToCustomer(CustomerDtoV1 dtoV1);

    CustomerDtoV1 customerToDto(Customer customer);
}
