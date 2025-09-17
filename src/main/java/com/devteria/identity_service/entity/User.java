package com.devteria.identity_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

//Đánh dấu class là 1 table dùng annotation là @Entity
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    //Cần 1 annotation để định nghĩa Id
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Dùng annotation để định nghĩa Id là UUID
     String id;
     String username;
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
     Set<String> roles;

}
