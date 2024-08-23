package org.therapazes.luisaoproject.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.dto.MailBodyDto;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendSimpleMessage(MailBodyDto mailBodyDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBodyDto.to());
        message.setSubject(mailBodyDto.subject());
        message.setText(mailBodyDto.text());

        javaMailSender.send(message);
    }
}
