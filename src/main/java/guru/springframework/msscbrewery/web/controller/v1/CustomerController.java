package guru.springframework.msscbrewery.web.controller.v1;

import guru.springframework.msscbrewery.services.v1.CustomerService;
import guru.springframework.msscbrewery.web.model.v1.CustomerDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDtoV1> getCustomer(@PathVariable("customerId") UUID customerId) {
        return ResponseEntity.ok(customerService.fetchCustomerByID(customerId));
    }

    @PostMapping
    public ResponseEntity<Void> handlePost(@RequestBody CustomerDtoV1 customerDto) {
        CustomerDtoV1 saved = customerService.saveNewCustomer(customerDto);
        return ResponseEntity.created(URI.create("/api/v1/customers/" + saved.getId())).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> handleUpdate(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDtoV1 customerDto) {
        customerService.updateCustomer(customerId, customerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("customerId") UUID customerId) {
        customerService.delete(customerId);
    }
}
