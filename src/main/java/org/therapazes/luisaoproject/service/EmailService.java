package org.therapazes.luisaoproject.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.therapazes.luisaoproject.dto.MailBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public EmailService(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Async
    public void sendEmailWithTemplate(String to, String subject, Map<String, Object> variables) throws MessagingException {
        Context context = new Context();
        context.setVariables(variables);
        String body = springTemplateEngine.process("index", context);

        MailBody mailBody = MailBody.builder()
                .to(to)
                .subject(subject)
                .text(body)
                .isHtml(true)
                .build();

        sendEmail(mailBody);
    }

    public void sendEmail(MailBody mailBody) throws MessagingException {
        if (mailBody.isHtml()) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true for multipart

            try {
                helper.setTo(mailBody.to());
                helper.setSubject(mailBody.subject());
                helper.setText(mailBody.text(), true); // true for HTML
                javaMailSender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        } else {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailBody.to());
            message.setSubject(mailBody.subject());
            message.setText(mailBody.text());
            javaMailSender.send(message);
        }
    }
}

