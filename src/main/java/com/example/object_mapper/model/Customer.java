package com.example.object_mapper.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue
    private Long customerId;

    @NotBlank(message = "Клиента точно как-нибудь зовут")
    private String firstName;
    private String lastName;
    @Email(message = "Проверьте форматирование имейла")
    @NotBlank(message = "Без имейла никак")
    private String email;
    private String contactNumber;

    public Customer(){}
}
