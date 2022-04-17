package com.yemreu.main.dto;

import com.yemreu.main.annotation.PasswordMatches;
import com.yemreu.main.annotation.UniqueTc;
import com.yemreu.main.annotation.UniqueUsername;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@PasswordMatches
public class UserRegisterDto {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    @UniqueUsername
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;
    // This field is a string only because
    // If the user registered himself by laboratory, that means he can be either laboratory and user.
    // If the user registered himself by user, then the user will get user role only.
    // Role assignment will be implemented in save method in user service.
    private String role;
    @UniqueTc
    @Length(min = 11, max = 11)
    private String tc;
}
