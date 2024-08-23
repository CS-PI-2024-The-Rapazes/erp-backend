package org.therapazes.luisaoproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.dto.ChangePasswordDto;
import org.therapazes.luisaoproject.dto.MailBodyDto;
import org.therapazes.luisaoproject.entities.ForgotPassword;
import org.therapazes.luisaoproject.entities.User;
import org.therapazes.luisaoproject.repositories.ForgotPasswordRepository;
import org.therapazes.luisaoproject.repositories.UserRepository;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class ForgotPasswordService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public String verifyEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Coloque um email válido!"));

        MailBodyDto mailBodyDto = MailBodyDto.builder()
                .to(email)
                .text("Este é o código para recuperar a senha:" + otpGenerator())
                .subject("Código para recuperar senha")
                .build();

        ForgotPassword fp = ForgotPassword.builder()
                .otp(otpGenerator())
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000)) //1 MINUTO E 10SEGUNDOS
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBodyDto);
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

    public String changePasswordHandler(ChangePasswordDto changePasswordDto, String email) {
        if (!Objects.equals(changePasswordDto.password(), changePasswordDto.repeatPassword())) {
            throw new RuntimeException("Por favor coloque a senha novamente!");
        }
        String encodedPassword = passwordEncoder.encode(changePasswordDto.password());
        userRepository.updatePassoword(email, encodedPassword);

        return "A senha foi alterada com sucesso!";
    }
}

