package org.therapazes.luisaoproject.controllers;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import org.therapazes.luisaoproject.service.ForgotPasswordService;
import org.therapazes.luisaoproject.dto.ChangePassword;


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
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword) {
        try {
            return ResponseEntity.ok(forgotPasswordService.changePasswordHandler(changePassword));
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

