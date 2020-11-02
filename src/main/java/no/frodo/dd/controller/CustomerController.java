package no.frodo.dd.controller;

import no.frodo.dd.domain.CustomerRequestDTO;
import no.frodo.dd.domain.CustomerResponseDTO;
import no.frodo.dd.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("c")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    CustomerResponseDTO saveCustomer(@RequestBody
                                     CustomerRequestDTO customerRequestDTO) {

        CustomerResponseDTO customerResponseDTO
                = customerService.saveCustomer(customerRequestDTO);

        return  customerResponseDTO;
    }
}
