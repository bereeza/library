package com.example.library.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSaveDto {
    private String email;
    private String password;
}
