package org.therapazes.luisaoproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.therapazes.luisaoproject.dto.ChangePasswordDto;
import org.therapazes.luisaoproject.services.ForgotPasswordService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/forgotPassword")

public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;


    //enviar mensagem para o email de verificacao
    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(forgotPasswordService.verifyEmail(email));
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        try {
            return ResponseEntity.ok(forgotPasswordService.verifyOtp(otp, email));
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePasswordDto changePasswordDto, @PathVariable String email) {
        try {
            return ResponseEntity.ok(forgotPasswordService.changePasswordHandler(changePasswordDto, email));
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

