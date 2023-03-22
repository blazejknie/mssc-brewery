package guru.springframework.msscbrewery.services.v1;

import guru.springframework.msscbrewery.web.model.v1.CustomerDtoV1;

import java.util.UUID;

public interface CustomerService {
    CustomerDtoV1 fetchCustomerByID(UUID customerId);

    CustomerDtoV1 saveNewCustomer(CustomerDtoV1 customerDto);

    void updateCustomer(UUID customerId, CustomerDtoV1 customerDto);

    void delete(UUID customerId);
}
