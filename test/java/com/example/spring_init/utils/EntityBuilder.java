package com.example.spring_init.utils;

import com.example.spring_init.model.request.CreatePersonModel;
import com.example.spring_init.model.request.CreateUserModel;

public class EntityBuilder {

    public static CreateUserModel getCreateUserModel(String name, String password) {
        return CreateUserModel.builder().
                name(name)
                .password(password)
                .build();
    }

    public static CreatePersonModel getcreatePersonModel(String name, int pincode) {
        return CreatePersonModel.builder()
                .name(name)
                .pinCode(pincode)
                .build();
    }


}
