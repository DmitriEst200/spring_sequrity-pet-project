package org.spring.social.testdrive.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @Builder
    public LoginDto(@NotBlank String email, @NotBlank String password) {
        this.email = email;
        this.password = password;
    }
}
