package no.frodo.dd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    Integer dd_id;
    String customerId;
    String customerName;
    String customerType;
    String customerOwner;
    Date customer_creationdate;
}
