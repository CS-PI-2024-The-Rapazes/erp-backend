package org.therapazes.luisaoproject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.therapazes.luisaoproject.dto.MailBody;
import org.therapazes.luisaoproject.entities.ForgotPassword;
import org.therapazes.luisaoproject.entities.User;
import org.therapazes.luisaoproject.repositories.ForgotPasswordRepository;
import org.therapazes.luisaoproject.repositories.UserRepository;
import org.therapazes.luisaoproject.service.EmailService;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;

    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, ForgotPasswordRepository forgotPasswordRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
    }

    int otp = otpGenerator();
    //enviar mensagem para o email de verificacao
    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Coloque um email válido!"));
        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("Este é o código para recuperar a senha:" + otp)
                .subject("Código para recuperar senha")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000)) //1 MINUTO E 70SEGUNDOS
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok("Código enviado!");
    }
    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999); //6 digitos aleatorios entre esses valores
    }
}
