package com.zerobase.user.domain;

import lombok.Data;

@Data
public class SignInForm {
    private String email;
    private String password;
}
