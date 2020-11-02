package no.frodo.dd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CustomerDTO  {

    String customerId;
    String customerName;
    String customerType;
    String customerOwner;
    String customerCreationDate;

}
