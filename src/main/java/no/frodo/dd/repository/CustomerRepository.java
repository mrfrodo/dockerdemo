package no.frodo.dd.repository;

import no.frodo.dd.domain.CustomerEntity;
import no.frodo.dd.domain.CustomerRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from customer", Integer.class);
    }

    public int save(CustomerRequestDTO customerRequestDTO) {
        int updated = 0;
        try {
            updated = jdbcTemplate.update(
                    "insert into public.customer (customer_id, customer_name, customer_type, customer_owner) values(?,?,?,?)",
                    customerRequestDTO.getCustomerId(), customerRequestDTO.getCustomerName(), customerRequestDTO.getCustomerOwner(), customerRequestDTO.getCustomerType());
        } catch (Exception e) {
            System.out.println("  ** Error " + e.getMessage());
        }

        return updated;

    }

    public Optional<CustomerEntity> findById(String cid) {
        return jdbcTemplate.queryForObject(
                "select dd_id,customer_id,customer_name,customer_type,customer_owner,customer_creationdate from customer where customer_id = ?",
                new Object[]{cid},
                (rs, rowNum) ->
                        Optional.of(new CustomerEntity(
                                rs.getLong("dd_id"),
                                rs.getString("customer_id"),
                                rs.getString("customer_name"),
                                rs.getString("customer_type"),
                                rs.getString("customer_owner"),
                                rs.getDate("customer_creationndate")
                        ))
        );
    }

    public List<CustomerEntity> findAll() {
        List<CustomerEntity> customerEntity = jdbcTemplate.query(
                "select dd_id,customer_id,customer_name,customer_type,customer_owner,customer_creationdate from customer",
                (rs, rowNum) ->
                        new CustomerEntity(
                                rs.getLong("dd_id"),
                                rs.getString("customer_id"),
                                rs.getString("customer_name"),
                                rs.getString("customer_type"),
                                rs.getString("customer_owner"),
                                rs.getDate("customer_creationdate")
                        )
        );

        return customerEntity;
    }
}
