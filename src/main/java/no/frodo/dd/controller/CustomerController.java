package no.frodo.dd.controller;

import no.frodo.dd.domain.CustomerEntity;
import no.frodo.dd.domain.CustomerRequestDTO;
import no.frodo.dd.domain.CustomerResponseDTO;
import no.frodo.dd.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("c")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    int saveCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.saveCustomer(customerRequestDTO);
    }

    @DeleteMapping
    int deleteCustomer(@RequestParam String cid) {
        return customerService.deleteCustomer(cid);
    }

    @GetMapping
    CustomerResponseDTO getCustomer(@RequestParam String cid) {
        return customerService.getCustomer(cid);
    }

    @GetMapping("all")
    List<CustomerEntity> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
