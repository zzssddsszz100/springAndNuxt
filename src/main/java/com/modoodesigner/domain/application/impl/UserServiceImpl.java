package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.UserService;
import com.modoodesigner.domain.application.commands.UserRegisterCommand;
import com.modoodesigner.domain.model.user.*;
import com.modoodesigner.domain.common.mail.MailManager;
import com.modoodesigner.domain.common.mail.MessageVariable;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRegistrationManagement userRegistrationManagement;
//    private final DomainEventPublisher domainEventPublisher;
    private final MailManager mailManager;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("유저를 찾을수 없습니다.");
        }
        User user;
        if (username.contains("@")) {
            user = userRepository.findByEmailAddress(username);
        } else {
            user = userRepository.findByUsername(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException(username + "의 유저를 찾을 수 없습니다.");
        }
        return new SimpleUser(user);
    }

    @Override
    public User findById(UserId userId) {
        return userRepository.findById(userId.value()).orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public void register(UserRegisterCommand command) throws UserRegistrationException {
        User newUser = userRegistrationManagement.register(
                command.getUsername(),
                command.getEmailAddress(),
                command.getPassword());

        sendWelcomeMessage(newUser);
//        domainEventPublisher.publish(new UserRegisteredEvent(newUser, command));

    }

    private void sendWelcomeMessage(User user) {
        mailManager.send(
                user.getEmailAddress(),
                "나는디자이너에 가입해주셔서 감사합니다.",
                "welcome.ftl",
                MessageVariable.from("user", user)
        );
    }
}
