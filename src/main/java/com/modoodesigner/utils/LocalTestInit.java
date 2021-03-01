package com.modoodesigner.utils;

import com.modoodesigner.domain.application.PendantService;
import com.modoodesigner.domain.application.UserService;
import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.application.commands.UserRegisterCommand;
import com.modoodesigner.domain.common.model.Role;
import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.model.part.pendant.MountingType;
import com.modoodesigner.domain.model.user.User;
import com.modoodesigner.web.payload.PendantRegistrationPayload;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
@Profile({"local","test"})
@RequiredArgsConstructor
public class LocalTestInit {
    private final InitMemberService initMemberService;
    private final InitPendantService initPendantService;
    @PostConstruct
    public void init(){
        initMemberService.init();
        initPendantService.init();
    }

    @Component
    @Slf4j
    static class InitMemberService{
        @Autowired
        UserService userService;

        @SneakyThrows
        @Transactional
        public void init(){
            log.debug("------------------------------------------");
            log.debug("유저생성");
            String name = "user";
            UserRegisterCommand command = new UserRegisterCommand(name, name + "@test.com", "user");
            userService.register(command);
            command = new UserRegisterCommand("admin", "admin@test.com", "admin");
            userService.register(command);
            User admin = userService.findByUsername("admin");
            admin.addRoles(new Role("ROLE_ADMIN"));
            log.debug("유저생성완료");
            log.debug("------------------------------------------");
        }
    }

    @Component
    @Slf4j
    static class InitPendantService{
        @Autowired
        PendantService pendantService;

        @SneakyThrows
        @Transactional
        public void init(){
            log.debug("------------------------------------------");
            log.debug("펜던트 생성");
            PendantRegisterCommand command = PendantRegisterCommand.builder()
                    .name("테스트펜던트")
                    .buyPrice(1000)
                    .stock(100)
                    .color(PlatingColor.PINK)
                    .material(Material.SILVER)
                    .mountingType(MountingType.SINGLE)
                    .build();
            pendantService.register(command);
            log.debug("------------------------------------------");
        }
    }
}
