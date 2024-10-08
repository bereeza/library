package com.example.library.dto.user;

import com.example.library.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserInfoDto {
    private long id;
    private String email;
    private Role role;
}
