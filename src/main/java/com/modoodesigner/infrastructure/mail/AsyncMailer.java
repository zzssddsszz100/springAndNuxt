package com.modoodesigner.infrastructure.mail;

import com.modoodesigner.domain.common.mail.Mailer;
import com.modoodesigner.domain.common.mail.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncMailer implements Mailer {
    private final JavaMailSender javaMailSender;
    @Async
    @Override
    public void send(Message message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            if (StringUtils.isNoneBlank(message.getFrom())) {
                mailMessage.setFrom(message.getFrom());
            }
            if (StringUtils.isNoneBlank(message.getSubject())) {
                mailMessage.setSubject(message.getSubject());
            }
            if (StringUtils.isNoneBlank(message.getBody())) {
                mailMessage.setText(message.getBody());
            }
            if (message.getTo() != null) {
                mailMessage.setTo(message.getTo());
            }

            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            log.error("메일발송에 실패하였습니다.", e);
        }

    }
}
