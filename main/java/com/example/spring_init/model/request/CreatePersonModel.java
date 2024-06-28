package com.example.spring_init.model.request;

import com.example.spring_init.model.mongo.Person;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreatePersonModel {

    private String id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 5, max = 100, message = "Name should be > 5 characters and < 100 characters")
    private String name;

    private String address;

    private Person.State state;

    @NotNull(message = "Pin code cannot be empty")
    private int pinCode;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@\\.(.+){3}$", message = "Invalid Email-Id provided")
    private String email;


    public Person toPerson() {
        return Person.builder()
                .name(name)
                .address(address)
                .state(state)
                .pinCode(pinCode)
                .email(email)
                .build();
    }
}
