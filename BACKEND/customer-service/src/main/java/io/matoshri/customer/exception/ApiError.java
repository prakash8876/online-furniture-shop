package io.matoshri.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private LocalDateTime timestamp;
    private String path;
    private String error;
    private Integer status;

}
