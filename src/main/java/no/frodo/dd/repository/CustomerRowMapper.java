package no.frodo.dd.repository;

import no.frodo.dd.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        Object dd_id = rs.getObject("dd_id");
        Object customer_name = rs.getObject("customer_name");
        Object customer_type = rs.getObject("customer_type");
        Object customer_owner = rs.getObject("customer_owner");
        Object customer_creationdate = rs.getObject("customer_creationdate");
        return customer;
    }
}
