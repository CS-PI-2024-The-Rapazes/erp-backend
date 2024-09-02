package org.therapazes.luisaoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationRequestDto {
    private String email;
    private String password;
}