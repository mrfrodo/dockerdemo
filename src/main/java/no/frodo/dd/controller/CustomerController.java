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
        System.out.println("** save");
        return customerService.saveCustomer(customerRequestDTO);
    }

    @PutMapping
    int updateCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        System.out.println("** save");
        return customerService.updateCustomer(customerRequestDTO);
    }

    @DeleteMapping
    int deleteCustomer(@RequestParam String cid) {
        System.out.println("** delete");
        return customerService.deleteCustomer(cid);
    }

    @GetMapping
    CustomerResponseDTO getCustomer(@RequestParam String cid) {
        System.out.println("** get one");
        CustomerResponseDTO customer = customerService.getCustomer(cid);
        CustomerResponseDTO customer2 = customerService.getCustomer2(cid);
        return customer;
    }

    @GetMapping("all")
    List<CustomerEntity> getAllCustomers() {
        System.out.println("** get all");
        return customerService.getAllCustomers();
    }
}
