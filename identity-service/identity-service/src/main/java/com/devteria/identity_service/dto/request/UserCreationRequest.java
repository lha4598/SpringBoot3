package com.devteria.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    //Validate username
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    //tạo validation tối thiểu 8 kí tự
    @Size(min = 8,message = "INVALID_PASSWORD")
    String password;
    String firstName;
    String lastName;
    LocalDate dob;


}
