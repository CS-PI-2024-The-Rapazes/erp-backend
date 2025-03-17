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
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final Logger logger = Logger.getLogger(EmailService.class.getName());
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    /**
     * Envia um e-mail com um template Thymeleaf renderizado.
     * @param to destinatário
     * @param subject assunto do e-mail
     * @param variables variáveis para o template
     * @throws MessagingException erro ao enviar o e-mail
     */
    @Async
    public void sendEmailWithTemplate(String to, String subject, Map<String, Object> variables) throws MessagingException {
        Context context = new Context();
        context.setVariables(variables);
        String body = springTemplateEngine.process("index", context);

        MailBodyDto mailBodyDto = createMailBodyDto(to, subject, body, true);

        sendEmail(mailBodyDto);
    }

    @Async
    public void sendEmail(MailBodyDto mailBodyDto) throws MessagingException {
        if (mailBodyDto.isHtml()) {
            sendHtmlEmail(mailBodyDto);
        } else {
            sendSimpleEmail(mailBodyDto);
        }
    }
    
    private void sendHtmlEmail(MailBodyDto mailBodyDto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setTo(mailBodyDto.to());
            helper.setSubject(mailBodyDto.subject());
            helper.setText(mailBodyDto.text(), true);

            Optional.ofNullable(new ClassPathResource("static/images/logoCoxinha.png"))
                    .ifPresent(imageResource -> {
                        try {
                            helper.addInline("logoCoxinha", imageResource);
                        } catch (MessagingException e) {
                            logger.warning("Erro ao adicionar imagem inline: " + e.getMessage());
                        }
                    });

            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.severe("Erro ao enviar e-mail HTML: " + e.getMessage());
            throw e;
        }
    }
    
    private void sendSimpleEmail(MailBodyDto mailBodyDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBodyDto.to());
        message.setSubject(mailBodyDto.subject());
        message.setText(mailBodyDto.text());

        javaMailSender.send(message);
    }

    private MailBodyDto createMailBodyDto(String to, String subject, String body, boolean isHtml) {
        return MailBodyDto.builder()
                .to(to)
                .subject(subject)
                .text(body)
                .isHtml(isHtml)
                .build();
    }
}
