package org.therapazes.luisaoproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.therapazes.luisaoproject.dto.MailBody;
import org.therapazes.luisaoproject.entities.ForgotPassword;
import org.therapazes.luisaoproject.entities.User;
import org.therapazes.luisaoproject.repositories.ForgotPasswordRepository;
import org.therapazes.luisaoproject.repositories.UserRepository;
import org.therapazes.luisaoproject.service.EmailService;
import org.therapazes.luisaoproject.utils.ChangePassword;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
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
    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Coloque um email válido!"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user).orElseThrow(() -> new RuntimeException("OTP inválido do email: " + email));

        if(fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getFpid());
            return new ResponseEntity<>("OTP Expirou!", HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok("OTP verificado!");
    }


    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword, @PathVariable String email) {
        if(!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
            return new ResponseEntity<>("Por favor coloque a senha novamente!", HttpStatus.EXPECTATION_FAILED);
        }
        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassoword(email, encodedPassword);

        return ResponseEntity.ok("A senha foi alterada com sucesso!");
    }
    

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999); //6 digitos aleatorios entre esses valores
    }
}
