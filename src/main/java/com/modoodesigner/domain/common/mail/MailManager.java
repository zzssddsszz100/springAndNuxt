package com.modoodesigner.domain.common.mail;

public interface MailManager {

    /**
     *
     * @param emailAddress 받는사람의 이메일 주소
     * @param subject 이메일 제목
     * @param template 템플릿 이름
     * @param variables 메세지 변수들
     */
    void send(String emailAddress, String subject, String template, MessageVariable... variables);
}
