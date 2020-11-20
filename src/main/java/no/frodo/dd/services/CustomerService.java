package no.frodo.dd.services;

import no.frodo.dd.domain.CustomerEntity;
import no.frodo.dd.domain.CustomerRequestDTO;
import no.frodo.dd.domain.CustomerResponseDTO;
import no.frodo.dd.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    public CustomerResponseDTO saveOrUpdateCustomer(CustomerRequestDTO customerRequestDTO) {

        LocalDateTime now = LocalDateTime.now();
        if (customerRequestDTO.getCustomerId() == null) {
            // Save new customer
            String customerId = createCustomerId(customerRequestDTO);
            int saved = customerRepository.save(customerRequestDTO, customerId, now);
            if (saved != 0) {
                CustomerResponseDTO customer = getCustomer2(customerId);
                return customer;
            }
        } else {
            // Update existing customer
            CustomerResponseDTO customerResponseDTO = getCustomer2(customerRequestDTO.getCustomerId());
            if (customerResponseDTO != null) {
                int updated = customerRepository.update(customerRequestDTO, customerRequestDTO.getCustomerId(), now);
                if (updated != 0) {
                    CustomerResponseDTO customer = getCustomer2(customerRequestDTO.getCustomerId());
                    return customer;
                }
            }
        }

        return null;
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

    @Cacheable(value = "getCustomer2")
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
        customerResponseDTO.
                setCustomerUpdateDate(customerEntity.getCustomer_updatedate().toLocalDateTime());
        return customerResponseDTO;
    }

    public CustomerResponseDTO saveManyCustomers(List<CustomerRequestDTO> customerRequestDTO) {
        return null;
    }
}
