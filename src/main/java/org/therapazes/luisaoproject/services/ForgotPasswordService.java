package org.therapazes.luisaoproject.services;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.dto.ChangePassword;
import org.therapazes.luisaoproject.entities.ForgotPassword;
import org.therapazes.luisaoproject.entities.User;
import org.therapazes.luisaoproject.repositories.ForgotPasswordRepository;
import org.therapazes.luisaoproject.repositories.UserRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ForgotPasswordService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;


    public String verifyEmail(String email) throws MessagingException {
        String randomID = UUID.randomUUID().toString();

        String recoveryURL = "http://localhost:8080/user/account-recovery?email=" + email + "&sec=" + randomID;

        Map<String, Object> variables = new HashMap<>();
        variables.put("otp", recoveryURL);


        forgotPasswordRepository.findByUserEmail(email).ifPresentOrElse(e -> {
            e.setCode(randomID);
            e.setExpirationTime(new Date(System.currentTimeMillis() + 300 * 1000));
            forgotPasswordRepository.save(e);
        }, () -> {
            User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Coloque um email válido!"));

            ForgotPassword fp = ForgotPassword.builder()
                    .code(randomID)
                    .expirationTime(new Date(System.currentTimeMillis() + 300 * 1000)) // 3 MINUTOS
                    .user(user)
                    .build();
            forgotPasswordRepository.save(fp);
        });

        try {
            emailService.sendEmailWithTemplate(email, "Código para recuperar senha", variables);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return "Código enviado!";
    }

    public String changePasswordHandler(ChangePassword changePassword) {
        ForgotPassword fp = forgotPasswordRepository.findByUserEmail(changePassword.email()).orElseThrow(() -> new UsernameNotFoundException("Coloque um email válido!"));

        if (fp.getCode().equals(changePassword.code())) {
            String encodedPassword = passwordEncoder.encode(changePassword.password());
            userRepository.updatePassoword(changePassword.email(), encodedPassword);
            return "A senha foi alterada com sucesso!";

        } else {
            throw new RuntimeException("As senhas não coincidem");
        }
    }
}

