package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.v1.CustomerDtoV1;
import guru.springframework.msscbrewery.web.model.v2.CustomerDtoV2;

import java.util.UUID;

public interface CustomerService {
    CustomerDtoV1 fetchCustomerByID(UUID customerId);

    CustomerDtoV1 saveNewCustomer(CustomerDtoV2 customerDto);

    void updateCustomer(UUID customerId, CustomerDtoV2 customerDto);

    void delete(UUID customerId);
}
