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

    Long dd_id;
    String customer_id;
    String customer_name;
    String customer_type;
    String customer_owner;
    Date customer_creationdate;
}
