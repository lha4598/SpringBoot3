package com.devteria.identity_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity //anotation Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // generate 1 giá trị bất kì cho id vì id các users có thể trùng nhau
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;
    @ElementCollection //@ElementCollection để khai báo rằng thuộc tính
                        // roles là một collection chứa các giá trị đơn giản (ở đây là String).
    Set<String> roles;


}
