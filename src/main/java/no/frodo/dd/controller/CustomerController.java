package no.frodo.dd.controller;

import no.frodo.dd.domain.CustomerEntity;
import no.frodo.dd.domain.CustomerRequestDTO;
import no.frodo.dd.domain.CustomerResponseDTO;
import no.frodo.dd.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("c")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    HttpServletRequest request;

    @PostMapping
    CustomerResponseDTO saveCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        System.out.println("** save");
        return customerService.saveCustomer(customerRequestDTO);
    }

    @PutMapping
    int updateCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        System.out.println("** save");
        return customerService.updateCustomer(customerRequestDTO);
    }

    @DeleteMapping("/one")
    int deleteCustomer(@RequestParam String cid) {
        System.out.println("** delete one");
        return customerService.deleteCustomer(cid);
    }

    @DeleteMapping()
    int deleteAllCustomer() {
        System.out.println("** delete all");
        return customerService.deleteAllCustomer();
    }

    @GetMapping
    CustomerResponseDTO getCustomer(@RequestParam String cid) {
        System.out.println("** get one");
        //CustomerResponseDTO customer = customerService.getCustomer(cid);
        CustomerResponseDTO customer2 = customerService.getCustomer2(cid);
        return customer2;
    }

    @GetMapping("all")
    List<CustomerEntity> getAllCustomers() {
        System.out.println("** get all");
        return customerService.getAllCustomers();
    }
}
