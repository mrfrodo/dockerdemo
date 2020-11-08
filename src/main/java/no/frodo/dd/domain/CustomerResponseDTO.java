package no.frodo.dd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    String customerId;
    String customerName;
    String customerType;
    String customerOwner;
}
