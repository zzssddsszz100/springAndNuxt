package com.modoodesigner.domain.common.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultMailManager implements MailManager {

    private final String mailFrom = "test@test.com";
    private final Mailer mailer;
    private final Configuration configuration;

    @Override
    public void send(String emailAddress, String subject, String template, MessageVariable... variables) {
        String messageBody = createMessageBody(template, variables);
        SimpleMessage message = new SimpleMessage(emailAddress, subject, messageBody, mailFrom);
        mailer.send(message);
    }

    private String createMessageBody(String templateName, MessageVariable... variables) {
        try {
            Template template = configuration.getTemplate(templateName);
            Map<String,Object> model = new HashMap<>();
            if (variables != null) {
                for (MessageVariable variable : variables) {
                    model.put(variable.getKey(), variable.getValue());
                }
            }
            return FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
         } catch (Exception e) {
            log.error("메일 메세지 템플릿 만들기가 실패하였습니다 템플릿 이름 : ["+templateName+"]",e);
            return null;
        }

    }
}
