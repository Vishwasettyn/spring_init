package com.example.spring_init.model.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(toBuilder = true)
@Document(collection = "user")
public class UserModel {
    String id;
    @Indexed
    String name;
    String password;
    String role;

}
