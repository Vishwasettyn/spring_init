package com.example.spring_init.model.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(toBuilder = true)
@Document(collection = "person")
public class Person {

    @Id
    String id;

    @Indexed
    String name;

    String address;

    State state;

    int pinCode;

    String email;
    public enum State {
        KA, TN, AP, TS, KL, MH
    }
}