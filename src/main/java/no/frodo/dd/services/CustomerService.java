package no.frodo.dd.services;

import no.frodo.dd.domain.CustomerEntity;
import no.frodo.dd.domain.CustomerRequestDTO;
import no.frodo.dd.domain.CustomerResponseDTO;
import no.frodo.dd.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelMapper;

    public int saveCustomer(CustomerRequestDTO customerRequestDTO) {

        LocalDateTime customerCreationDate = LocalDateTime.now();
        System.out.println(customerCreationDate.toString());
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime.toString());
        String customerId = createCustomerId(customerRequestDTO);
        return customerRepository.save(customerRequestDTO, customerId, customerCreationDate);
    }

    public int updateCustomer(CustomerRequestDTO customerRequestDTO) {
        return 0;
    }

    public int deleteCustomer(String cid) {
        return customerRepository.deleteCustomerByCustomerId(cid);
    }

    public int deleteAllCustomer() {
        return customerRepository.deleteAll();
    }

    public CustomerResponseDTO getCustomer(String cid) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(cid);
        if (optionalCustomer.isPresent()) {
            CustomerEntity customerEntity = optionalCustomer.get();
            CustomerResponseDTO customerResponseDTO = convertToDto(customerEntity);
            return customerResponseDTO;
        }
        return null;
    }

    public CustomerResponseDTO getCustomer2(String cid) {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findByIdWithCustomRowMapper(cid);
        if (optionalCustomer.isPresent()) {
            CustomerEntity customerEntity = optionalCustomer.get();
            CustomerResponseDTO customerResponseDTO = convertToDto(customerEntity);
            return customerResponseDTO;
        }
        return null;
    }

    public List<CustomerEntity> getAllCustomers() {
        List<CustomerEntity> allCustomers = customerRepository.findAll();
        return allCustomers;
    }

    private String createCustomerId(CustomerRequestDTO customerRequestDTO) {
        String customerName = customerRequestDTO.getCustomerName();
        String[] splitted = customerName.split(" ");
        String customerId= splitted[0]+"_"+splitted[1];
        return customerId;

    }

    protected CustomerResponseDTO convertToDto(CustomerEntity customerEntity) {
        CustomerResponseDTO customerResponseDTO = modelMapper.map(customerEntity, CustomerResponseDTO.class);
        customerResponseDTO.
                setCustomerCreationDate(customerEntity.getCustomer_creationdate().toLocalDateTime());
        return customerResponseDTO;
    }


}
