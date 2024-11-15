package com.mycompany.controller;

import com.mycompany.exception.CustomerNotFoundException;
import com.mycompany.model.query.CustomerQuery;
import com.mycompany.model.request.CustomerRequest;
import com.mycompany.model.response.CustomerResponse;
import com.mycompany.service.CustomerService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/customers")
@Slf4j
@RequiredArgsConstructor
class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    Page<CustomerResponse> getAllCustomers(PageRequest pageRequest) {
        CustomerQuery customerQuery = new CustomerQuery(pageRequest);
        return customerService.findAllCustomers(customerQuery);
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return customerService
                .findCustomerById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Validated CustomerRequest customerRequest) {
        CustomerResponse response = customerService.saveCustomer(customerRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/api/customers/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id, @RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable Long id) {
        return customerService
                .findCustomerById(id)
                .map(customer -> {
                    customerService.deleteCustomerById(id);
                    return ResponseEntity.ok(customer);
                })
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
