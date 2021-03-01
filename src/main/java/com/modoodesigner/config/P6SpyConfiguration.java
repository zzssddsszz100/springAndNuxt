package com.modoodesigner.config;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.event.JdbcEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@Profile({"local","test"})
public class P6SpyConfiguration {
    @Bean
    public JdbcEventListener myListener() {
        return new JdbcEventListener() {
            @Override
            public void onAfterGetConnection(ConnectionInformation connectionInformation, SQLException e) {
                System.out.println("JDBC 커넥션");
            }

            @Override
            public void onAfterConnectionClose(ConnectionInformation connectionInformation, SQLException e) {
                System.out.println("JDBC 커넥션 종료");
            }
        };
    }
}
