package no.frodo.dd.services;

import no.frodo.dd.domain.CustomerEntity;
import no.frodo.dd.domain.CustomerRequestDTO;
import no.frodo.dd.domain.CustomerResponseDTO;
import no.frodo.dd.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public int saveCustomer(CustomerRequestDTO customerRequestDTO) {
        return customerRepository.save(customerRequestDTO);

    }

    public CustomerResponseDTO getCustomer(String cid) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(cid);
        CustomerEntity customerEntity = optionalCustomer.get();

        return null;
    }

    public List<CustomerEntity> getAllCustomers() {
        List<CustomerEntity> allCustomers = customerRepository.findAll();
        return allCustomers;
    }
}
