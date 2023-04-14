package io.matoshri.customer.dto;

import lombok.*;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequest implements Serializable {

    private Long id;
    private String contact;
    private String customerName;
    private String address;
    @Email(message = "Enter proper email address")
    private String email;


}
