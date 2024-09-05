package org.therapazes.luisaoproject.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.dto.MailBodyDto;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    @Async
    public void sendEmailWithTemplate(String to, String subject, Map<String, Object> variables) throws MessagingException {
        Context context = new Context();
        context.setVariables(variables);
        String body = springTemplateEngine.process("index", context);

        MailBodyDto mailBodyDto = MailBodyDto.builder()
                .to(to)
                .subject(subject)
                .text(body)
                .isHtml(true)
                .build();

        sendEmail(mailBodyDto);
    }

    @Async
    public void sendEmail(MailBodyDto mailBodyDto) throws MessagingException {
        if (mailBodyDto.isHtml()) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true for multipart

            try {
                helper.setTo(mailBodyDto.to());
                helper.setSubject(mailBodyDto.subject());
                helper.setText(mailBodyDto.text(), true); // true for HTML

                ClassPathResource imageResource = new ClassPathResource("static/images/logoCoxinha.png");

                helper.addInline("logoCoxinha", imageResource);

                javaMailSender.send(message);

            } catch (MessagingException e) {
                e.printStackTrace();
            }

        } else {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(mailBodyDto.to());
            message.setSubject(mailBodyDto.subject());
            message.setText(mailBodyDto.text());

            javaMailSender.send(message);
        }
    }

}

