package com.prakhar.blogapp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Data
public class AuthRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

}
