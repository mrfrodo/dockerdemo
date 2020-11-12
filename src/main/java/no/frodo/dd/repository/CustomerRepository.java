package no.frodo.dd.repository;

import no.frodo.dd.domain.CustomerEntity;
import no.frodo.dd.domain.CustomerRequestDTO;
import no.frodo.dd.domain.CustomerResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int count() {
        return jdbcTemplate .queryForObject("select count(*) from customer", Integer.class);
    }

    public int save(CustomerRequestDTO customerRequestDTO, String customerId, LocalDateTime now) {
        int saved = 0;
        try {
            String SQL = "insert into public.customer (customer_id, customer_name, customer_type, " +
                    "customer_owner, customer_creationdate, customer_upda" +
                    "tedate) values(?,?,?,?,?,?)";
            saved = jdbcTemplate.update(SQL, customerId, customerRequestDTO.getCustomerName(),
                    customerRequestDTO.getCustomerType(), customerRequestDTO.getCustomerOwner(), now, now);
        } catch (Exception e) {
            System.out.println("  ** Error " + e.getMessage());
        }

        return saved;

    }

    public int update(CustomerRequestDTO customerRequestDTO, String customerId, LocalDateTime now) {
        int updated = 0;
        try {
            String SQL = "update public.customer set customer_name = ?, customer_type = ?, customer_owner = ?, customer_updatedate = ? " +
                    "where customer_id = ?";
            updated = jdbcTemplate.update(SQL, customerRequestDTO.getCustomerName(),
                    customerRequestDTO.getCustomerType(), customerRequestDTO.getCustomerOwner(), now, customerRequestDTO.getCustomerId());
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
        String SQL = "delete from customer";
        return jdbcTemplate.update(SQL);
    }

    public Optional<CustomerEntity> findById(String cid) {
        try {
            String SQL = "select dd_id,customer_id,customer_name,customer_type,customer_owner," +
                    "customer_creationdate,customer_updatedate from customer where customer_id = ?";
            Optional<CustomerEntity> customerEntity = jdbcTemplate.queryForObject(
                    SQL,
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
                                rs.getTimestamp("customer_creationdate"),
                                rs.getTimestamp("customer_updatedate")
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
            String SQL = "select dd_id,customer_id,customer_name,customer_type,customer_owner,customer_creationdate,customer_updatedate" +
                    " from customer where customer_id = ?";
            CustomerEntity customerEntity = jdbcTemplate.queryForObject(SQL, new Object[]{cid}, new CustomerRowMapper());
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
        String SQL = "select dd_id,customer_id,customer_name,customer_type,customer_owner,customer_creationdate,customer_updatedate " +
                "from customer";
        List<CustomerEntity> customerEntity = jdbcTemplate.query(
                SQL,
                (rs, rowNum) ->
                        new CustomerEntity(
                                (Integer)rs.getObject("dd_id"),
                                rs.getString("customer_id"),
                                rs.getString("customer_name"),
                                rs.getString("customer_type"),
                                rs.getString("customer_owner"),
                                rs.getTimestamp("customer_creationdate"),
                                rs.getTimestamp("customer_updatedate"))
        );

        return customerEntity;
    }
}
