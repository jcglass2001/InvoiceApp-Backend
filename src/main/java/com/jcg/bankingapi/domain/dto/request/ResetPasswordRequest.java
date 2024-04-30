package com.jcg.bankingapi.domain.dto.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String password;
    private String confirmPassword;
}
