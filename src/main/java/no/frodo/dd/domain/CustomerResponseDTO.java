package no.frodo.dd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    String customerId;
    String customerName;
    String customerType;
    String customerOwner;
    LocalDateTime customerCreationDate;
    LocalDateTime customerUpdateDate;
}
