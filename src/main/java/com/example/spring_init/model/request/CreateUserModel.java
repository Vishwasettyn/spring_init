package com.example.spring_init.model.request;

import com.example.spring_init.model.mongo.UserModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.apache.catalina.util.StringUtil;
import org.springframework.util.StringUtils;


@Data
@Builder(toBuilder = true)
public class CreateUserModel {

    String id;

    @NotNull(message = "User name cannot be null")
    String name;

    @NotNull(message = "Password cannot be null")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password should contain minimum eight characters, at least one letter and one number")
    String password;

    String role;

    public UserModel toUserModel(){
        return UserModel.builder()
                .name(name)
                .password(password)
                .role(StringUtils.isEmpty(role) ? "USER" : role)
                .build();
    }
}
