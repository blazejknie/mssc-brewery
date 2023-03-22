package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.services.v2.CustomerService;
import guru.springframework.msscbrewery.web.model.v1.CustomerDtoV1;
import guru.springframework.msscbrewery.web.model.v2.CustomerDtoV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceV2 implements CustomerService {

    public CustomerDtoV1 fetchCustomerByID(UUID customerId) {
        return CustomerDtoV1.builder().id(UUID.randomUUID()).name("Adam").build();
    }

    @Override
    public CustomerDtoV1 saveNewCustomer(CustomerDtoV2 customerDto) {
        return CustomerDtoV1.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDtoV2 customerDto) {
        log.debug("update customer: " + customerDto);
    }

    @Override
    public void delete(UUID customerId) {
        log.debug("deleting customer");
    }


}
