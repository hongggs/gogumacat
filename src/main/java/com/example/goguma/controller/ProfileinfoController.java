package com.example.goguma.controller;

import com.example.goguma.dto.PasswordCheckDto;
import com.example.goguma.dto.ProfileUpdateDto;
import com.example.goguma.service.PwService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfileinfoController {

    private final PwService pwService;

    public ProfileinfoController(PwService pwService) {
        this.pwService = pwService;
    }

    @PostMapping("/profileinfo/check")
    public boolean password(PasswordCheckDto passwordCheckDto){
        boolean result = pwService.checkPw(passwordCheckDto);
        return result;
    }
    @PutMapping("/update_profile/{id}")
    public Long updateProfile(@PathVariable Long id, @RequestBody ProfileUpdateDto profileUpdateDto){
        pwService.update(id, profileUpdateDto);
        return id;
    }

}