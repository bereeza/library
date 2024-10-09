package com.example.library.controllers;

import com.example.library.dto.user.UserInfoDto;
import com.example.library.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/u")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<UserInfoDto> getCurrentUser() {
        return ResponseEntity.ok(profileService.getCurrentUser());
    }

    @PatchMapping("/set-role")
    public ResponseEntity<UserInfoDto> setAdmin(@RequestParam("email") String email) {
        return ResponseEntity.ok(profileService.setAdminRole(email));
    }
}