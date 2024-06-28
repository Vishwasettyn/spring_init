package com.example.spring_init.model.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Builder(toBuilder = true)
public class UserModel {
    String id;
    @Indexed
    String name;
    String password;
    String role;

}
