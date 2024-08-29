package org.therapazes.luisaoproject.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.dto.MailBody;
import org.therapazes.luisaoproject.entities.ForgotPassword;
import org.therapazes.luisaoproject.entities.User;
import org.therapazes.luisaoproject.repositories.ForgotPasswordRepository;
import org.therapazes.luisaoproject.repositories.UserRepository;
import org.therapazes.luisaoproject.utils.ChangePassword;

import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ForgotPasswordService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public String verifyEmail(String email) throws MessagingException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Coloque um email válido!"));

        Integer otp = otpGenerator();
        Map<String, Object> variables = new HashMap<>();
        variables.put("otp", otp);

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000)) // 1 MINUTO E 10 SEGUNDOS
                .user(user)
                .build();

        emailService.sendEmailWithTemplate(email, "Código para recuperar senha", variables);
        forgotPasswordRepository.save(fp);

        return "Código enviado!";
    }

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999); //6 digitos aleatorios entre esses valores
    }

    public String verifyOtp(Integer otp, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Coloque um email válido!"));

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user).orElseThrow(() -> new RuntimeException("OTP inválido do email: " + email));

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(fp.getFpid());
            throw new RuntimeException("OTP Expirou!");
        }

        return "OTP verificado!";
    }

    public String changePasswordHandler(ChangePassword changePassword, String email) {
        if(!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
            throw new RuntimeException("Por favor coloque a senha novamente!");
        }
        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userRepository.updatePassoword(email, encodedPassword);

        return "A senha foi alterada com sucesso!";
    }
}

