package no.frodo.dd.services;

import no.frodo.dd.controller.CustomerController;
import no.frodo.dd.domain.CustomerEntity;
import no.frodo.dd.domain.CustomerRequestDTO;
import no.frodo.dd.domain.CustomerResponseDTO;
import no.frodo.dd.repository.CustomerRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

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
        if (splitted.length == 2) {
            String customerId= splitted[0]+"_"+splitted[1];
            return customerId;
        } else {
            String createdCustomCustomerId = createCustomCustomerId(splitted[0]);
            return createdCustomCustomerId;
        }
    }

    private String createCustomCustomerId(String s) {
        String substring = s.substring(0, s.length()-1);
        StringBuilder customerId = new StringBuilder();
        customerId.append(s); customerId.append("_");customerId.append(substring);
        if (substring.equals("")) {
            String randomString = generateRandomThreeLetterString();
            customerId.append(randomString);
        }

        return customerId.toString();
    }

    private String generateRandomThreeLetterStringUNBOUNDED() {
        byte[] array = new byte[3]; // length is bounded by 3
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        logger.warn("Created generate string {}", generatedString);
        return generatedString;
    }

    private String generateRandomThreeLetterString() {
        int length = 3;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        logger.warn("Created generate string {}", generatedString);
        return generatedString;
    }

    protected CustomerResponseDTO convertToDto(CustomerEntity customerEntity) {
        CustomerResponseDTO customerResponseDTO = modelMapper.map(customerEntity, CustomerResponseDTO.class);
        customerResponseDTO.
                setCustomerCreationDate(customerEntity.getCustomer_creationdate().toLocalDateTime());
        customerResponseDTO.
                setCustomerUpdateDate(customerEntity.getCustomer_updatedate().toLocalDateTime());
        return customerResponseDTO;
    }

    public int saveManyCustomers(List<CustomerRequestDTO> customerRequestDTO) {
       for (CustomerRequestDTO crDTO : customerRequestDTO) {
           saveOrUpdateCustomer(crDTO);
        }
       return 0;
    }
}
