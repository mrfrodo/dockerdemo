package no.frodo.dd.repository;

import no.frodo.dd.domain.CustomerEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerRowMapper implements RowMapper<CustomerEntity> {

    @Override
    public CustomerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerEntity customer = new CustomerEntity();
        Object dd_id = rs.getObject("dd_id");
        Object customer_name = rs.getObject("customer_name");
        Object customer_id = rs.getObject("customer_id");
        Object customer_type = rs.getObject("customer_type");
        Object customer_owner = rs.getObject("customer_owner");
        Object customer_creationdate = rs.getObject("customer_creationdate");

        customer.setDd_id((Integer)dd_id);
        customer.setCustomerId((String)customer_id);
        customer.setCustomerName((String)customer_name);
        customer.setCustomerType((String)customer_type);
        customer.setCustomerOwner((String)customer_owner);
        customer.setCustomer_creationdate((Date)customer_creationdate);

        return customer;
    }
}
