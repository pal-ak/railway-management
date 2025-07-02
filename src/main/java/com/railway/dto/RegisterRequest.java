package com.railway.dto;

import com.railway.model.Role;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private Role role; // "ADMIN" or "USER"
}
