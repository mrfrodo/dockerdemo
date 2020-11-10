package no.frodo.dd.repository;

import no.frodo.dd.domain.CustomerEntity;
import no.frodo.dd.domain.CustomerRequestDTO;
import no.frodo.dd.domain.CustomerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int count() {
        return jdbcTemplate .queryForObject("select count(*) from customer", Integer.class);
    }

    public int save(CustomerRequestDTO customerRequestDTO, String customerId) {
        int updated = 0;
        try {
            updated = jdbcTemplate.update("insert into public.customer (customer_id, customer_name, customer_type, customer_owner) values(?,?,?,?)",
                    customerId, customerRequestDTO.getCustomerName(), customerRequestDTO.getCustomerType(), customerRequestDTO.getCustomerOwner());
        } catch (Exception e) {
            System.out.println("  ** Error " + e.getMessage());
        }

        return updated;

    }

    public int deleteCustomerByCustomerId(String cid) {
        int update = jdbcTemplate.update("delete from customer where customer_id = ?", cid);
        return update;
    }

    public int deleteAll () {
        return jdbcTemplate.update("delete from customer");
    }

    public int update(CustomerResponseDTO customerResponseDTO) {
        int update = jdbcTemplate.update(
                "update customer set customer_name = ?, customer_type = ?, customer_owner = ?, customer_creationdate = ? " +
                        "where custoner_id = ?");
        return update;
    }

    public Optional<CustomerEntity> findById(String cid) {
        try {
            Optional<CustomerEntity> customerEntity = jdbcTemplate.queryForObject(
                    "select dd_id,customer_id,customer_name,customer_type,customer_owner,customer_creationdate from customer where customer_id = ?",
                    new Object[]{cid},
                    (rs, rowNum) ->
                    {
                        Object dd_id = rs.getObject("dd_id");
                        return Optional.of(new CustomerEntity(
                                (Integer) dd_id,
                                rs.getString("customer_id"),
                                rs.getString("customer_name"),
                                rs.getString("customer_type"),
                                rs.getString("customer_owner"),
                                rs.getDate("customer_creationdate")
                        ));
                    }
            );
            return customerEntity;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("EMPTY DATA BACK ERROR " + e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("UNKNOWN ERROR " + e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<CustomerEntity> findByIdWithCustomRowMapper(String cid) {
        try {
            String sql = "select dd_id,customer_id,customer_name,customer_type,customer_owner,customer_creationdate from customer where customer_id = ?";
            CustomerEntity customerEntity = jdbcTemplate.queryForObject(sql, new Object[]{cid}, new CustomerRowMapper());
            return Optional.of(customerEntity);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("EMPTY DATA BACK ERROR " + e.getMessage());
            return Optional.empty();
        } catch (Exception e) {
            System.out.println("UNKNOWN ERROR " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<CustomerEntity> findAll() {
        List<CustomerEntity> customerEntity = jdbcTemplate.query(
                "select dd_id,customer_id,customer_name,customer_type,customer_owner,customer_creationdate from customer",
                (rs, rowNum) ->
                        new CustomerEntity(
                                (Integer)rs.getObject("dd_id"),
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
